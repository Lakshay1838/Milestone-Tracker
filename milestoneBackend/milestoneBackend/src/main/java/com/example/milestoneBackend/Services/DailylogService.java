package com.example.milestoneBackend.Services;

import com.example.milestoneBackend.Entities.Dailylog;
import com.example.milestoneBackend.Entities.Milestone;
import com.example.milestoneBackend.Repositories.DailylogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailylogService {

    @Autowired
    private DailylogRepository dailylogRepository;
    @Autowired
    private MilestoneService milestoneService;

    public Dailylog createDailyLog(String milestoneId,Dailylog dailylog){
        Milestone milestone = milestoneService.getMilestoneByIdForUser(milestoneId);
        Dailylog justSaved = dailylogRepository.save(dailylog);
        milestone.getDailyLogs().add(justSaved);
        milestoneService.createNewMilestone(milestone);
        return dailylogRepository.findById(justSaved.getId()).orElse(null);
    }
}
