package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class TransferFailedException extends BaseException {
    public TransferFailedException() {
        super("转账失败", 30004);
    }
}

