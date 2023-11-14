package com.neusoft.elmboot.exception;

public class UsernameUserIdRepeatedException extends BaseException{
    public UsernameUserIdRepeatedException() {
        super("用户名重复", 40001);
    }
}
