package com.example.demo.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "friends")
@Data
public class Friend {
    // For a chat, we will assume that a chat between 2 friends will be stored at the URL
    // .../friends/userOneId/userTwoId/
    @Id
    private String id;

    private String userOneId;

    private String userTwoId;

    // This is 1, if there exists a messaging channel between the 2 friends.
    private Boolean chat = false;

    // This tells us the date that the message was created at.
    @CreatedDate
    private Instant created_at;

}
