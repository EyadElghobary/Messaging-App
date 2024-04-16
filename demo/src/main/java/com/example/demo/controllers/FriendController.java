package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Friend;
import com.example.demo.models.User;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.User;


@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendService friendService;

    // The @AuthenticationPrincipal annotation might be useful here.
    // This annotation passes in the currently authenticated User as a parameter to a
    // function. Look at UserController.java class for an idea.
    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody Map<String, Object> userInfo) {

        String username = (String) userInfo.get("username");

        // This is temporary until I find a way to make the security of
        // my application work
        // TODO: Make sure to change this line when you implement the security
        // userOneId should contain the Id of the current user.
        String userOneId = "";

        // Using 'username', we want to know if that 'username' exists, and if they
        // do create a friend between the current user and the user with 'username'
        String userTwoId = userRepository.findByUsername(username)
        .map(User::getId)
        .orElse(null);

        if (userTwoId == null) {
            return ResponseEntity.badRequest().body("User Not Found");
        }

        if (friendRepository.findFriendship(userOneId, userTwoId).isPresent()) {
            return ResponseEntity.badRequest().body(String.format("%s is already your friend", username));
        }

        friendService.createFriend(userOneId, userTwoId);
        return ResponseEntity.ok("Added Friend");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getMethodName() {

        // TODO: Make sure to change this line when you implement the security
        // userOneId should contain the Id of the current user.
        String userOneId = "";

        List<Friend> friends = friendRepository.findFriends(userOneId);
        return ResponseEntity.ok(friends);
    }

    // I need to create a method that will allow a chat to be created.
    
    
}
