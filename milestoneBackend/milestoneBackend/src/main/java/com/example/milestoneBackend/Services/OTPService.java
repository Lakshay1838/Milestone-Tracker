package com.example.milestoneBackend.Services;

import com.example.milestoneBackend.Entities.EmailOTP;
import com.example.milestoneBackend.Repositories.EmailOTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailOTPRepository emailOTPRepository;

    public String generateOTP(String email){
        String otp = String.format("%04d", new Random().nextInt(10000));

        EmailOTP emailOTP = new EmailOTP();
        emailOTP.setEmail(email);
        emailOTP.setOtp(otp);
        emailOTP.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        emailOTPRepository.save(emailOTP);


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify Milestone Tracker using OTP");
        message.setText("Your OTP is : " + otp + "\nIT is valid for % minutes");
        mailSender.send(message);

        return "OTP sent to mail";
    }
}
