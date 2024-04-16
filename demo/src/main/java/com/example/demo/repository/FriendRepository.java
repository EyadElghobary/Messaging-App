package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.models.Friend;
import com.example.demo.models.User;

public interface FriendRepository extends MongoRepository<Friend, String>{

    @Query("{$or: [{'userOneId': ?0, 'userTwoId': ?1}, {'userOneId': ?1, 'userTwoId': ?0}]}")
    Optional<Friend> findFriendship(String userOneId, String userTwoId);

    @Query("{$or: [{'userOneId': ?0}, {'userTwoId': ?0}]}")
    List<Friend> findFriends(String userId);
    
}
