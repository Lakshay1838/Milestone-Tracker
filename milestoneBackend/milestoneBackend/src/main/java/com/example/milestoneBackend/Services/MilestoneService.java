package com.example.milestoneBackend.Services;

import com.example.milestoneBackend.Entities.Dailylog;
import com.example.milestoneBackend.Entities.Milestone;
import com.example.milestoneBackend.Entities.User;
import com.example.milestoneBackend.Repositories.MilestoneRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;
    @Autowired
    private UserService userService;

    private String email;

//    create by id
    public Milestone createNewMilestone(Milestone milestone){
        User user = userService.getUserByEmail();
        List<Milestone> milestoneList = user.getMilestones();
        milestone.setStartDate(LocalDateTime.now());
        System.out.println(milestone.getTotalDays());
        Milestone justCreated = milestoneRepository.save(milestone);
        milestoneList.add(justCreated);
        User u = userService.createUser(user);
        return milestoneRepository.findById(milestone.getId()).orElse(null);
    }
//    get by id for user
    public Milestone getMilestoneByIdForUser(String id){
//        User user = userService.getUserByEmail();
        List<Milestone> milestoneList = userService.getUserByEmail().getMilestones();
        for(Milestone milestone:milestoneList){
            if(milestone.getId().equals(new ObjectId(id))){
                return milestone;
            }
        }
        return null;
    }
//    update by id - can only update name and the days assigned once only.
    public Milestone updateById(String id,Milestone updatedMilestone,boolean arr[]){
        List<Milestone> milestoneList = userService.getUserByEmail().getMilestones();
        Milestone milestoneToUpdate = null;

        for(Milestone milestone:milestoneList){
            if(milestone.getId().equals(new ObjectId(id))){
                milestoneToUpdate = milestone;
                break;
            }
        }

        if(milestoneToUpdate != null){
            arr[0] = true;
            if(updatedMilestone.getTitle() != null){
                milestoneToUpdate.setTitle(updatedMilestone.getTitle());
            }
            if(updatedMilestone.getTotalDays() > 0 && !milestoneToUpdate.isUpdatedOnce()){
                milestoneToUpdate.setUpdatedOnce(true);
                milestoneToUpdate.setTotalDays(updatedMilestone.getTotalDays());
            }
            return milestoneRepository.save(milestoneToUpdate);
        }
        return milestoneToUpdate;
    }
//    delete by id
    public boolean deleteById(String id){
        User user = userService.getUserByEmail();
        List<Milestone> milestoneList = user.getMilestones();

        for(Milestone milestone:milestoneList){
            if(milestone.getId().equals(new ObjectId(id))){
                milestoneList.remove(milestone);
                user = userService.createUser(user);
                milestoneRepository.deleteById(new ObjectId(id));
                return true;
            }
        }
        return false;
    }

//    get all dailyLogs
    public List<Dailylog> getALlDailyLogsForMilestone(String milestoneId){
        Milestone milestone = milestoneRepository.findById(new ObjectId(milestoneId)).orElse(null);
        if(milestone != null){
            return milestone.getDailyLogs();
        }
        return null;
    }
}
