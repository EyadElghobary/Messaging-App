package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Friend;
import com.example.demo.repository.FriendRepository;

@Service
public class FriendService {
    
    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public Friend createFriend(String userOneId, String userTwoId) {

        Friend friend = new Friend();
        friend.setUserOneId(userOneId);
        friend.setUserTwoId(userTwoId);

        return friendRepository.save(friend);
    }

    
}
