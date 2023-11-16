package com.neusoft.elmboot.exception.credit;

import com.neusoft.elmboot.exception.BaseException;

public class UserHasSignedException extends BaseException {
    public UserHasSignedException() {
        super("您今日已经签到过了", 20000);
    }
}
