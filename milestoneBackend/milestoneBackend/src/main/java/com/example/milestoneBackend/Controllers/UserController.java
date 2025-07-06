package com.example.milestoneBackend.Controllers;

import com.example.milestoneBackend.Entities.User;
import com.example.milestoneBackend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<User> getUserByEmail(){
        User u = userService.getUserByEmail();
        return new ResponseEntity<>(u,HttpStatus.FOUND);
    }
    @PutMapping("/")
    public ResponseEntity<User> updateUserByEmail(@RequestBody User user){
        User u = userService.updateUserByEmail(user);
        return new ResponseEntity<>(u,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/")
    public ResponseEntity<?> deleteByEmail(){
        if(userService.deleteByEmail()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
