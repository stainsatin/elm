package com.neusoft.elmboot;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(Throwable.class)
    public String handler(Throwable e){
        e.printStackTrace();
        return "服务器异常";
    }
}
