package com.neusoft.elmboot.exception.order;

import com.neusoft.elmboot.exception.BaseException;

public class OrderIdUserIdNotMatchedException extends BaseException {
    public OrderIdUserIdNotMatchedException() {
        super("该订单不是您创建的！", 40008);
    }
}
