package com.example.milestoneBackend.Entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Component
@Document(collection = "Dailylogs")
public class Dailylog {

    @Id
    private ObjectId id;
    private LocalDate date;
    private int hoursInvested;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHoursInvested() {
        return hoursInvested;
    }

    public void setHoursInvested(int hoursInvested) {
        this.hoursInvested = hoursInvested;
    }
}
