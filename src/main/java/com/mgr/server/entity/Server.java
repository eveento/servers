package com.mgr.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Server {

    private long id;
    private String context;
    private int[] delay = {1,2,3,4,5,6,7,8,9,10,15,20,30,40,50};
    final Integer TIME = 1000;

    public Server(long incrementAndGet, String format) {
        this.id = incrementAndGet;
        this.context = format;
    }

    public Integer chooseRandomDelay(){
        Random rand = new Random();
        List<Integer> list = Arrays.stream(delay).boxed().collect(Collectors.toList());
        return rand.nextInt(list.size()) * TIME;
    }
    public Integer chooseDelay(Integer _delay){
        List<Integer> list = Arrays.stream(delay).boxed().collect(Collectors.toList());
        return list.indexOf(_delay) * TIME;
    }


}
