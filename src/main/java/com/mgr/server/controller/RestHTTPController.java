package com.mgr.server.controller;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import com.mgr.server.services.ServerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping(value = "/http")
public class RestHTTPController {

    @Autowired
    private Memory memory;

    @Autowired
    private Server server;

    @Autowired
    private ServerService serverService;

    private static final Logger log = LogManager.getLogger(RestHTTPController.class);

    @RequestMapping(value = "/uuid", method = RequestMethod.GET)
    public String getUUId(@RequestParam(name = "id") Integer _id, String _method) {
        try {
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

            memory.setName(randomUUIDString);
            memory.setPercent(0.0);
            memory.setResponse("Ready to start");
            memory.setBody(_id.toString());
            memory.setMethod(_method);
            server.setMap(randomUUIDString, memory);
            Memory a = (server.getMap().get(randomUUIDString));
            serverService.updateProgress(randomUUIDString);
            log.info("Add to memory. UUID: " + randomUUIDString);
            log.info("key: " + a.getName());
            return randomUUIDString;
        } catch (Exception e) {
            return "Cannot load to memory data";
        }
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public String getResponse(@RequestParam(name = "uuid") String _uuid) {
        try {
            Memory task = serverService.findTask(_uuid);
            return task.getPercent().toString();
        } catch (Exception e) {
            return "Task does not exist in memory. " + e.getMessage();
        }
    }
}
