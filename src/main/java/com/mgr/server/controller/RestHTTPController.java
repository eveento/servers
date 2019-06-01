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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/http")
public class RestHTTPController {
    private static final Logger log = LogManager.getLogger(RestHTTPController.class);
    @Autowired
    private Memory memory;
    @Autowired
    private Server server ;
    @Autowired
    private ServerService serverService ;
    private Integer delay;

    private final AtomicLong counterRand = new AtomicLong();
    private final AtomicLong counterPredictable = new AtomicLong();

    @RequestMapping(value = "/rand", method = RequestMethod.POST)
    public String httpRandomDelay() throws InterruptedException {
        long amount = counterRand.incrementAndGet();
        this.delay = serverService.chooseRandomDelay();
        Thread.sleep(this.delay);
        log.info("counter: " + amount + " ,delay: " + this.delay.toString());
        return "counter: " + amount + " ,delay: " + this.delay.toString();
    }

    @RequestMapping(value = "/predictable", method = RequestMethod.POST)
    public String httpDelay(@RequestParam(name = "delay") Integer _delay) throws InterruptedException {
        long amount = counterPredictable.incrementAndGet();
        this.delay = serverService.chooseDelay(_delay);
        Thread.sleep(this.delay);
        log.info("counter: " + amount + " ,delay: " + this.delay.toString());
        return "counter: " + amount + " ,delay: " + this.delay.toString();
    }

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
            Memory task;
            task = serverService.findTask(_uuid);
            return "Task " + task.getName() + " has be done with " + task.getPercent() + " %";
        } catch (Exception e) {
            return "Task does not exist in memory. " + e.getMessage();
        }
    }
}
