package com.example.milestoneBackend.Controllers;

import com.example.milestoneBackend.Entities.EmailOTP;
import com.example.milestoneBackend.Entities.User;
import com.example.milestoneBackend.Repositories.EmailOTPRepository;
import com.example.milestoneBackend.Repositories.UserRepository;
import com.example.milestoneBackend.Services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailOTPRepository emailOTPRepository;
    @Autowired
    private OTPService otpService;

    @PostMapping("/signup")
    public ResponseEntity<?>    signup(@RequestBody User user){
        if(userRepository.getByEmail(user.getEmail()) != null){
            return ResponseEntity.badRequest().body("User already exists");
        }

        user.setVerified(false);
        userRepository.save(user);

        otpService.generateOTP(user.getEmail());
        return ResponseEntity.ok("OTP sent to email");
    }

    @PostMapping("verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestParam String email,@RequestParam String otp){
        EmailOTP storedotp = emailOTPRepository.findByEmail(email).orElse(null);

        if(storedotp == null || !storedotp.getOtp().equals(otp) || storedotp.getExpiryTime().isBefore(LocalDateTime.now())){
            return ResponseEntity.badRequest().body("Invalid or Exired otp");
        }

        User user = userRepository.getByEmail(email);

        if(user == null){
            return ResponseEntity.badRequest().body("User not Found");
        }

        user.setVerified(true);
        userRepository.save(user);

        emailOTPRepository.delete(storedotp);

        return ResponseEntity.ok("Email verified successfully");
    }
}
