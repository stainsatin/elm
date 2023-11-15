package com.neusoft.elmboot.exception.user;

import com.neusoft.elmboot.exception.BaseException;

public class UsernameUserIdRepeatedException extends BaseException {
    public UsernameUserIdRepeatedException() {
        super("用户名重复", 40001);
    }
}
