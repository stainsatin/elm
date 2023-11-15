package com.neusoft.elmboot.exception.user;

import com.neusoft.elmboot.exception.BaseException;

public class UsernameNotFoundException extends BaseException {
    public UsernameNotFoundException() {
        super("未找到用户名", 40002);
    }
}
