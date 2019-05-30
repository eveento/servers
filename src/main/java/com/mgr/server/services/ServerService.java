package com.mgr.server.services;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;

import java.util.*;
import java.util.stream.Collectors;

public class ServerService extends TimerTask {

    private int[] delay = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 30, 40, 50};
    final Integer TIME = 1000;
    Server server = new Server();
    Memory current = new Memory(); // tu jest null ???? whyyyy
    double increment = 0.0;

    Timer timer = new Timer();
    TimerTask progressBar = new TimerTask() {
        @Override
        public void run() {
            if (current.getReady() || increment != 100.0) {
                increment = increment + 0.5;
                current.setPercent(increment);
            } else {
                current.setReady(true);
                increment = 0.0;
            }
        }
    };

    public void updateProgress(String _id) {
        current = server.getMap().get(_id);
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
