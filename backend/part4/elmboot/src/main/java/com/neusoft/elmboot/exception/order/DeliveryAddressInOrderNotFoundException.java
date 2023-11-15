package com.neusoft.elmboot.exception.order;

import com.neusoft.elmboot.exception.BaseException;

public class DeliveryAddressInOrderNotFoundException extends BaseException {
    public DeliveryAddressInOrderNotFoundException() {
        super("该配送地址不存在", 40006);
    }
}
