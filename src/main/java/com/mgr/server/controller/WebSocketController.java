package com.mgr.server.controller;

import com.mgr.server.entity.Server;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/user")
    @SendTo("/topic/user")
    public long getId(Server server){
        return server.getId();
    }
}
