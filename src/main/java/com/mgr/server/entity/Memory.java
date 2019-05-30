package com.mgr.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Memory {
    private String name;
    private String response;
    private Double percent;
    private String body;
    private String method;
}
