package com.mgr.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Server {
    private long id;
    private String context;
    public HashMap<String, Memory> map = new HashMap<>();

    public void setMap(String randomUUIDString, Memory memory) {
        map.put(randomUUIDString,memory);
    }
}
