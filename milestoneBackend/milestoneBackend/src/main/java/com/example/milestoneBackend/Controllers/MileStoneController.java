package com.example.milestoneBackend.Controllers;

import com.example.milestoneBackend.Entities.Milestone;
import com.example.milestoneBackend.Entities.User;
import com.example.milestoneBackend.Repositories.MilestoneRepository;
import com.example.milestoneBackend.Services.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/milestoneController")
@CrossOrigin(origins = "http://localhost:3000")
public class MileStoneController {

    @Autowired
    private MilestoneService milestoneService;

    @PostMapping("/")
    public ResponseEntity<Milestone> createNewMilestone(@RequestBody Milestone milestone){
        Milestone justCreated = milestoneService.createNewMilestone(milestone);
        if(justCreated != null){
            return new ResponseEntity<>(justCreated,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(justCreated,HttpStatus.CONFLICT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Milestone> getMilestoneById(@PathVariable String id){
        Milestone milestone = milestoneService.getMilestoneByIdForUser(id);
        if(milestone != null){
            return new ResponseEntity<>(milestone, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(milestone,HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateById(@PathVariable String id,@RequestBody Milestone milestone){
        boolean[] arr = new boolean[1];
        arr[0] = false;
        Milestone justSavedMilestone = milestoneService.updateById(id,milestone,arr);
        if(arr[0]){
            return new ResponseEntity<>(justSavedMilestone,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(milestoneService.getMilestoneByIdForUser(id),HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMilestone(@PathVariable String id){
        if(milestoneService.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
