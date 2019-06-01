package com.mgr.server.controller;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import com.mgr.server.services.ServerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping(value = "/http")
public class RestHTTPController {

    @Autowired
    private Server server;

    @Autowired
    private ServerService serverService;

    private static final Logger log = LogManager.getLogger(RestHTTPController.class);

    @RequestMapping(value = "/uuid", method = RequestMethod.GET)
    public ResponseEntity<String> getUUId(@RequestParam(name = "id") Integer _id, String _method) {
        try {
            Memory memory = new Memory();
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

            memory.setName(randomUUIDString);
            memory.setPercent(0.0);
            memory.setResponse("Ready to start");
            memory.setBody(_id.toString());
            memory.setMethod(_method);
            server.setMap(randomUUIDString, memory);
            serverService.updateProgressBar(memory);

            log.info("key: " + randomUUIDString);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(randomUUIDString);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public ResponseEntity<String> getResponse(@RequestParam(name = "uuid") String _uuid) {
        try {
            Memory task = serverService.findTask(_uuid);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(task.getPercent().toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
