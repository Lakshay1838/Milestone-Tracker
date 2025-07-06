package com.example.milestoneBackend.Entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.util.*;
import java.time.LocalDateTime;

@Data
@Component
public class Milestone {

    @Id
    private ObjectId id;
    private String Title;
    private LocalDateTime startDate;
    private int TotalDays;
    @DBRef
    private List<Dailylog> dailyLogs = new ArrayList<>();
    private boolean updatedOnce = false;

}
