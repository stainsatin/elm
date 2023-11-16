package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class UserHasNotCreatedWalletIdException extends BaseException {
    public UserHasNotCreatedWalletIdException() {
        super("用户未创建钱包", 30002);
    }
}
