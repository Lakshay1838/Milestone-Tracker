package com.example.milestoneBackend.Entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.*;
import java.time.LocalDateTime;

@Data
@Component
@Document(collection = "Milestones")
public class Milestone {

    @Id
    private ObjectId id;
    private String Title;
    private LocalDateTime startDate;
    private int TotalDays;
    @DBRef
    private List<Dailylog> dailyLogs = new ArrayList<>();
    private boolean updatedOnce = false;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getTotalDays() {
        return TotalDays;
    }

    public void setTotalDays(int totalDays) {
        TotalDays = totalDays;
    }

    public List<Dailylog> getDailyLogs() {
        return dailyLogs;
    }

    public void setDailyLogs(List<Dailylog> dailyLogs) {
        this.dailyLogs = dailyLogs;
    }

    public boolean isUpdatedOnce() {
        return updatedOnce;
    }

    public void setUpdatedOnce(boolean updatedOnce) {
        this.updatedOnce = updatedOnce;
    }
}
