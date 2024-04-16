package com.example.demo.models;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "groups")
@Data
public class Group {
    
    @Id
    private String id;

    private String name;

    private List<String> members;

    private String created_by;

    @CreatedDate
    private Instant created_at;

}
