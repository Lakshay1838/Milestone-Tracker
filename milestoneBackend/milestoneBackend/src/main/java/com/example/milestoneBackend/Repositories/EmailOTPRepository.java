package com.example.milestoneBackend.Repositories;

import com.example.milestoneBackend.Entities.EmailOTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EmailOTPRepository extends MongoRepository<EmailOTP,String>{
    Optional<EmailOTP> findByEmail(String email);
}
