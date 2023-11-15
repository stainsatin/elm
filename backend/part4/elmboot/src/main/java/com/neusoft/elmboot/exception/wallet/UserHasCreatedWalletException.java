package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class UserHasCreatedWalletException extends BaseException {
    public UserHasCreatedWalletException() {
        super("用户已经创建过钱包", 30000);
    }
}
