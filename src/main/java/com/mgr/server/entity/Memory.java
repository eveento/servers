package com.mgr.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Memory {
    private String name;
    private String response;
    private Double percent;
    private String body;
    private String method;
    private Boolean ready = false;

    public synchronized void setPercent( Double _percent){
        this.percent=_percent;
    }
    public synchronized Double getPercent(){
        return this.percent;
    }
}