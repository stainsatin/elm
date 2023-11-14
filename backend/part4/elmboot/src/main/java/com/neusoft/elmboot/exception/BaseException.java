package com.neusoft.elmboot.exception;


public class BaseException extends Exception{
    private String message;
    private int code;

    public BaseException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
}
