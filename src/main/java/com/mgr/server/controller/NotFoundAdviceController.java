package com.mgr.server.controller;

import com.mgr.server.exceptions.NotFoundHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdviceController {


    @ResponseBody
    @ExceptionHandler(NotFoundHandler.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFoundHandler(NotFoundHandler notFoundHandler){
        return notFoundHandler.getMessage();
    }
}
