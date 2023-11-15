package com.neusoft.elmboot.exception.user;

import com.neusoft.elmboot.exception.BaseException;

public class UserIdNotFoundException extends BaseException {
    public UserIdNotFoundException() {
        super("未找到用户ID", 40003);
    }
}
