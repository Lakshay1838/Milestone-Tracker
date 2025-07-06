package com.example.milestoneBackend.Entities;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexOptions;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Component
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    @NonNull
    @Indexed(unique = true)
    private String email;
    @NonNull
    private String password;

    @DBRef
    private List<Milestone> milestones = new ArrayList<>();
    private LocalDateTime created;
}
