package com.neusoft.elmboot.exception.order;

import com.neusoft.elmboot.exception.BaseException;

public class OrderHasPayedException extends BaseException {
    public OrderHasPayedException() {
        super("订单已支付", 40007);
    }
}
