package com.example.demo.repository;

import com.example.demo.models.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    
}
