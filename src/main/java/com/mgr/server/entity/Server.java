package com.mgr.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class Server {
    private long id;
    private String context;
    private HashMap<String, Memory> map = new HashMap<>();

    public void setMap(String randomUUIDString, Memory memory) {
        map.put(randomUUIDString,memory);
    }
}
