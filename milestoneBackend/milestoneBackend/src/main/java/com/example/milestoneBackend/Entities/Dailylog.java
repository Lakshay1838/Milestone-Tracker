package com.example.milestoneBackend.Entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class Dailylog {

    @Id
    private ObjectId id;
    private LocalDateTime date;
    private int hoursInvested;

}
