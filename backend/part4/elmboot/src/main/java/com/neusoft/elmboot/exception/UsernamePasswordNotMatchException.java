package com.neusoft.elmboot.exception;

public class UsernamePasswordNotMatchException extends  BaseException{
    public UsernamePasswordNotMatchException() {
        super("用户名密码不匹配", 40000);
    }
}
