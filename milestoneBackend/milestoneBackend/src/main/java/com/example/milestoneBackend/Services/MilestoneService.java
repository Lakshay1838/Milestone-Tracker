package com.example.milestoneBackend.Services;

import com.example.milestoneBackend.Entities.Milestone;
import com.example.milestoneBackend.Repositories.MilestoneRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

@Service
public class MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

//    create by id
    public Milestone createNewMilestone(Milestone milestone){
        return milestoneRepository.save(milestone);
    }
//    get by id
    public Milestone getMilestoneById(String id){
        return milestoneRepository.findById(new ObjectId(id)).orElse(null);
    }
//    update by id - can only update name and the days assigned once only.
    public Milestone updateById(String id,Milestone updatedMilestone){
        Milestone milestone = milestoneRepository.findById(new ObjectId(id)).orElse(null);
        if(milestone != null){
            if(updatedMilestone.getTitle() != null){
                milestone.setTitle(updatedMilestone.getTitle());
            }
            if(updatedMilestone.getTotalDays() != 0){
                milestone.setTotalDays(updatedMilestone.getTotalDays());
            }
            return milestoneRepository.save(milestone);
        }
        return null;
    }
//    delete by id
    public boolean deleteById(String id){
        Milestone milestone = milestoneRepository.findById(new ObjectId(id)).orElse(null);
        if(milestone != null){
            milestoneRepository.deleteById(new ObjectId(id));
            return true;
        }
        return false;
    }
}
