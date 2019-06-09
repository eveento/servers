package com.mgr.server.services;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import com.mgr.server.enums.Level;
import com.mgr.server.exceptions.NotFoundHandler;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CommonsLog
@Service
public class ServerService {

    @Autowired
    private Server server;

    private final AtomicLong counter = new AtomicLong();

    @Async
    public void updateProgressBar(Memory memory) throws InterruptedException {
        while (!memory.getReady() && memory.getPercent() != 100.0) {
            memory.setPercent(memory.getPercent() + 0.5);
//            Thread.sleep(100);
            primeNumbersTill(Level.LOW);
        }
        memory.setReady(true);
        log.info("number: " + counter.incrementAndGet());
    }

    private static boolean isPrimeBruteForce(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> primeNumbersTill(Level _level) {
        int _number;
        if (_level == null)
            throw new NotFoundHandler(" level");
        switch (_level) {
            case LOW:
                _number = 10000;
                break;
            case MEDIUM:
                _number = 50000;
                break;
            case HIGH:
                _number = 100000;
                break;
            default:
                _number = 1;
        }
        return primeNumbersBruteForce(_number);
    }

    private static List<Integer> primeNumbersBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public Memory findTask(String _uuid) {
        for (Iterator iter = server.getMap().entrySet().iterator(); iter.hasNext(); ) {
            if (server.getMap().containsKey(_uuid)) {
                return server.getMap().get(_uuid);
            }
        }
        throw new NotFoundHandler(_uuid);
    }

}
