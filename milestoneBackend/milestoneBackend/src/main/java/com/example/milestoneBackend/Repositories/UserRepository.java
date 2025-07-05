package com.example.milestoneBackend.Repositories;

import com.example.milestoneBackend.Entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User getByEmail(String email);
    void deleteByEmail(String email);
}
