package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class RechargeFailedException extends BaseException {
    public RechargeFailedException() {
        super("充值失败", 30009);
    }
}
