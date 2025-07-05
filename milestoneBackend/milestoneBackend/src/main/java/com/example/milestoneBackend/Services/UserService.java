package com.example.milestoneBackend.Services;

import com.example.milestoneBackend.Entities.User;
import com.example.milestoneBackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private String email;

//    get by id
//    get by email
    public User getUserByEmail(){
        return userRepository.getByEmail(email);
    }
//    update user by email
    public User updateUserByEmail(User user){
        User u = userRepository.getByEmail(email);
        if(u != null){
            if(user.getUsername() != null){
                u.setUsername(user.getUsername());
            }
            if(user.getEmail() != null){
                u.setEmail(user.getEmail());
            }
            if(user.getPassword() != null){
                u.setPassword(user.getPassword());
            }
            u.setCreated(LocalDateTime.now());
            return userRepository.save(u);
        }
        return null;
    }
//    delete user by email
    public boolean deleteByEmail(){
        User u = userRepository.getByEmail(email);
        if(u != null){
            userRepository.deleteByEmail(email);
            return true;
        }else {
            return false;
        }
    }
}
