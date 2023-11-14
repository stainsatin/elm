package com.neusoft.elmboot.handler;

import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result CustomBaseExceptionHandler(BaseException e){
        return Result.failure(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(Throwable.class)
    public Result handler(Throwable e){
        e.printStackTrace();
        return Result.failure(50000,e.getMessage());
    }
}
