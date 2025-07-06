package com.example.milestoneBackend.Repositories;

import com.example.milestoneBackend.Entities.Dailylog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailylogRepository extends MongoRepository<Dailylog, ObjectId> {

}
