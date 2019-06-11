package com.mgr.server.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemoryRequestDTO {

    private Integer _uuid;
    private String method;
}
