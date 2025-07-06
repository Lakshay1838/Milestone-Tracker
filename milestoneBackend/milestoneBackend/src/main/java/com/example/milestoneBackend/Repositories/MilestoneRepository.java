package com.example.milestoneBackend.Repositories;

import com.example.milestoneBackend.Entities.Milestone;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepository extends MongoRepository<Milestone, ObjectId> {
    
}
