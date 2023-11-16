package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class BalanceRemainNotEnoughException extends BaseException {
    public BalanceRemainNotEnoughException() {
        super("余额不足", 30003);
    }
}

