package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class CreateWalletFailedException extends BaseException {
    public CreateWalletFailedException() {
        super("创建钱包失败", 30001);
    }
}
