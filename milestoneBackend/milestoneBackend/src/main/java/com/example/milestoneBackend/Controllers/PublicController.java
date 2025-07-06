package com.example.milestoneBackend.Controllers;

import com.example.milestoneBackend.Entities.User;
import com.example.milestoneBackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("SpringBoot Application is Working", HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        User u = userService.createUser(user);
        return new ResponseEntity<>(u,HttpStatus.CREATED);
    }
}
