package com.mgr.server.services;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import com.mgr.server.exceptions.NotFoundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServerService extends TimerTask {

    private int[] delay = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 30, 40, 50};
    final Integer TIME = 1000;

    @Autowired
    private Server server;
    @Autowired
    private Memory current;

    double increment = 0.0;

    Timer timer = new Timer();
    TimerTask progressBar = new TimerTask() {
        @Override
        public void run() {
            if (current.getReady() && increment != 100.0) {
                increment = increment + 0.5;
                current.setPercent(increment);
            } else {
                current.setReady(true);
            }
        }
    };

    public Memory findTask(String _uuid) {
        for (Iterator iter = server.getMap().entrySet().iterator(); iter.hasNext(); ) {
            if (server.getMap().containsKey(_uuid)) {
                current = server.getMap().get(_uuid);
                break;
            } else {
                throw new NotFoundHandler(_uuid);
            }
        }
        return current;
    }

    public void updateProgress(String _id) {
        current = findTask(_id);
        timer.schedule(progressBar, 100, 100);
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


    @Override
    public void run() {

    }
}
