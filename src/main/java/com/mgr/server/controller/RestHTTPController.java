package com.mgr.server.controller;

import com.mgr.server.entity.Server;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Log4j
@RestController
@RequestMapping(value = "/http")
public class RestHTTPController {
    private static final Logger log = LogManager.getLogger(RestHTTPController.class);

    private Server server = new Server();
    private Integer delay;

    private final AtomicLong counterRand = new AtomicLong();
    private final AtomicLong counterPredictable = new AtomicLong();

    @RequestMapping(value = "/rand", method= RequestMethod.POST)
    public String httpRandomDelay() throws InterruptedException {
        long amount = counterRand.incrementAndGet();
        this.delay = server.chooseRandomDelay();
        Thread.sleep(this.delay);
        log.info("counter: " + amount  + " ,delay: " + this.delay.toString());
        return "counter: " + amount + " ,delay: " + this.delay.toString();
    }

    @RequestMapping(value = "/predictable", method= RequestMethod.POST)
    public String httpDelay(@RequestParam(name = "delay") Integer _delay) throws InterruptedException {
        long amount = counterPredictable.incrementAndGet();
        this.delay = server.chooseDelay(_delay);
        Thread.sleep(this.delay);
        log.info("counter: " + amount + " ,delay: " + this.delay.toString());
        return "counter: " + amount + " ,delay: " + this.delay.toString();
    }

}
