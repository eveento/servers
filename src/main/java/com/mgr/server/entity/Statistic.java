package com.mgr.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistic {

    private int maxClientNumber= 0;

    private List<List<AvaliableDTO>> availableClientList = Collections.synchronizedList(new ArrayList<>());

    public synchronized int getMaxClientNumber(){
        return maxClientNumber;
    }

    public synchronized void setMaxClientNumber(int maxClientNumber){
        this.maxClientNumber=maxClientNumber;
    }

    public synchronized void increamentMaxClient(){
        this.maxClientNumber++;
    }

}
