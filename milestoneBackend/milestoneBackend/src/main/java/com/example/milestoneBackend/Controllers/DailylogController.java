package com.example.milestoneBackend.Controllers;

import com.example.milestoneBackend.Entities.Dailylog;
import com.example.milestoneBackend.Services.DailylogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailylogController")
@CrossOrigin(origins = "https://localhost:5173")
public class DailylogController {

    @Autowired
    private DailylogService dailylogService;

    @PostMapping("/{milestoneId}")
    public Dailylog createNewLog(@PathVariable String milestoneId,@RequestBody Dailylog dailylog){
        return dailylogService.createDailyLog(milestoneId,dailylog);
    }
}
