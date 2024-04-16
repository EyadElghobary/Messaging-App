package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "friend_requests")
@Data
public class FriendRequest {
    @Id
    private String id;

    private String senderId;
    private String receiverId;

    private FriendRequestStatus status; // Use the enum here
}

