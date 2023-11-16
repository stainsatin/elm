package com.neusoft.elmboot.exception.wallet;

import com.neusoft.elmboot.exception.BaseException;

public class PayOrdersFailedException extends BaseException {
    public PayOrdersFailedException() {
        super("支付订单失败！", 30005);
    }
}
