package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.types.chat.ChatMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    
    @GetMapping("/chat/{groupNumber}")
    public ModelAndView getMethodName(@PathVariable String groupNumber) {
        ModelAndView modelAndView = new ModelAndView("chat/index");
        modelAndView.addObject("groupNumber", groupNumber);
        return modelAndView;
    }

    /*
     * The idea here is that when a user wants to send a message to all clients, they send the
     * payload to /app/chat.sendMessage. The @SendTo annotation then publishes this message to
     * /topic/public where all clients are listening.
     */
    @MessageMapping("/chat.sendMessage")
    // @SendTo("/topic/public")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        String destination = "/topic/" + chatMessage.getGroup();
        messagingTemplate.convertAndSend(destination, chatMessage);
    }

    /*
     * The idea here is that when a user joins, they send a payload chatMessage instance 
     * to /app/chat.addUser. The @SendTo annotation then publishes this message to
     * /topic/public where all clients are listening.
     */
    @MessageMapping("/chat.addUser")
    // @SendTo("/topic/public")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        String destination = "/topic/" + chatMessage.getGroup();
        messagingTemplate.convertAndSend(destination, chatMessage);
    }
}

