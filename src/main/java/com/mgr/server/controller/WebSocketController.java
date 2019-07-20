package com.mgr.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketController {

    @Autowired
    WebSocketMessageBrokerStats webSocketMessageBrokerStats;

    private AtomicLong atomicLong = new AtomicLong(0);

    @CrossOrigin
    @MessageMapping("/user")
    @SendTo("/topic/user")
    public void webSockerEndpoint(String id) {
//        System.out.println("Number: " + atomicLong.getAndIncrement());
        System.out.println("Session Stats info " +webSocketMessageBrokerStats.getWebSocketSessionStatsInfo());
    }

}
