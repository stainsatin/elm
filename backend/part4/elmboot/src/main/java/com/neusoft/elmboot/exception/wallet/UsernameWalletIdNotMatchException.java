package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class UsernameWalletIdNotMatchException extends BaseException {
    public UsernameWalletIdNotMatchException() {
        super("对方的用户名和钱包号码不匹配", 30003);
    }
}
