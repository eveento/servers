package com.mgr.server.services;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import com.mgr.server.exceptions.NotFoundHandler;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
@CommonsLog
@Service
public class ServerService {

    @Autowired
    private Server server;
    private int[] delay = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 30, 40, 50};
    private final Integer TIME = 1000;

    @Async
    public void updateProgressBar(Memory memory) throws InterruptedException {
        while (!memory.getReady() && memory.getPercent() != 100.0) {
            memory.setPercent(memory.getPercent() + 0.5);
            Thread.sleep(500);
            log.info("Id: " + memory.getName() + ", percent: " + memory.getPercent());
        }
        memory.setReady(true);
    }

    public Memory findTask(String _uuid) {
        for (Iterator iter = server.getMap().entrySet().iterator(); iter.hasNext(); ) {
            if (server.getMap().containsKey(_uuid)) {
                return server.getMap().get(_uuid);
            }
        }
        throw new NotFoundHandler(_uuid);
    }

    public Integer chooseRandomDelay() {
        Random rand = new Random();
        List<Integer> list = Arrays.stream(delay).boxed().collect(Collectors.toList());
        return rand.nextInt(list.size()) * TIME;
    }

    public Integer chooseDelay(Integer _delay) {
        List<Integer> list = Arrays.stream(delay).boxed().collect(Collectors.toList());
        return list.indexOf(_delay) * TIME;
    }
}
