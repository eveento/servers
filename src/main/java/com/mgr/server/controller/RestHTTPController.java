package com.mgr.server.controller;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

//@Log4j
@RestController
@RequestMapping(value = "/http")
public class RestHTTPController {
    private static final Logger log = LogManager.getLogger(RestHTTPController.class);

    private HashMap<String, Memory> map = new HashMap<>();
    private Memory memory = new Memory();
    private Server server = new Server();
    private Integer delay;

    private final AtomicLong counterRand = new AtomicLong();
    private final AtomicLong counterPredictable = new AtomicLong();

    @RequestMapping(value = "/rand", method = RequestMethod.POST)
    public String httpRandomDelay() throws InterruptedException {
        long amount = counterRand.incrementAndGet();
        this.delay = server.chooseRandomDelay();
        Thread.sleep(this.delay);
        log.info("counter: " + amount + " ,delay: " + this.delay.toString());
        return "counter: " + amount + " ,delay: " + this.delay.toString();
    }

    @RequestMapping(value = "/predictable", method = RequestMethod.POST)
    public String httpDelay(@RequestParam(name = "delay") Integer _delay) throws InterruptedException {
        long amount = counterPredictable.incrementAndGet();
        this.delay = server.chooseDelay(_delay);
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
            map.put(randomUUIDString, memory);
            log.info("Add to memory. UUID: " + randomUUIDString);
            Memory a = (map.get(randomUUIDString));
            log.info("key: " + a.getName());
            return randomUUIDString;
        } catch (Exception e) {
            return "Cannot load to memory data";
        }
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public String getResponse(@RequestParam(name = "uuid") String _uuid) {
        try {
            Memory response = map.get(_uuid);
            return "Task " + response.getName() + " has be done with " + response.getPercent() + " %";
        } catch (Exception e) {
            return "Task does not exist in memory. " + e.getMessage();
        }

    }
}
