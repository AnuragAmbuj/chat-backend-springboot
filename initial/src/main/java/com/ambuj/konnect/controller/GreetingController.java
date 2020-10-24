package com.ambuj.konnect.controller;

import com.ambuj.konnect.model.Sender;
import com.ambuj.konnect.model.Greeting;
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
