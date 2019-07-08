package com.mgr.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketController {

    private AtomicLong atomicLong = new AtomicLong(0);


    @CrossOrigin
    @MessageMapping("/user")
    @SendTo("/topic/user")
    public void webSockerEndpoint(String id) {
        System.out.println("Number: " + atomicLong.getAndIncrement());
    }


}
