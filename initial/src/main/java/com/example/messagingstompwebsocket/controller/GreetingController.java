package com.example.messagingstompwebsocket.controller;

import com.example.messagingstompwebsocket.model.Greeting;
import com.example.messagingstompwebsocket.model.Sender;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Sender sender){
        return new Greeting("Hello,"+sender.getName()+"!");
    }
}
