package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.constants.CommonConstants;
import com.example.messagingstompwebsocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chat/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chat/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put(CommonConstants.USERNAME_HEADER_ACCESSOR, chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.sendMessageToUser")
    @SendToUser("/topic/chat/person")
    public ChatMessage sendMessageToUser(@Payload ChatMessage chatMessage){
        //TODO
        return chatMessage;
    }

}
