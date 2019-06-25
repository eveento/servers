//package com.mgr.server.controller;
//
//import com.mgr.server.entity.Memory;
//import com.mgr.server.entity.MemoryRequestDTO;
//import com.mgr.server.entity.Server;
//import com.mgr.server.services.ServerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class WebSocketController {
//
//    @Autowired
//    private Server server;
//
//    @Autowired
//    private ServerService serverService;
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public String greeting(MemoryRequestDTO memoryRequestDTO) throws Exception {
//
//        try {
//            Memory memory = new Memory();
//            memory.setName(memoryRequestDTO.get_uuid().toString());
//            memory.setPercent(0.0);
//            memory.setResponse("Ready to start");
//            memory.setMethod(memoryRequestDTO.getMethod());
//            server.setMap(memoryRequestDTO.get_uuid().toString(), memory);
//            serverService.updateProgressBar(memory);
//
//            return "ok";
//
//        } catch (Exception e) {
//            return "Not work";
//        }
//
//    }
//}
