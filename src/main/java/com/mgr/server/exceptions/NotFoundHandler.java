package com.mgr.server.exceptions;

public class NotFoundHandler extends RuntimeException{


    public NotFoundHandler(){
        super("Not found");
    }
}
