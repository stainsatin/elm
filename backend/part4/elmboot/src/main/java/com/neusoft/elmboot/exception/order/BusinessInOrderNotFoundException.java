package com.neusoft.elmboot.exception.order;

import com.neusoft.elmboot.exception.BaseException;

public class BusinessInOrderNotFoundException extends BaseException {
    public BusinessInOrderNotFoundException() {
        super("未在购物车中找到该商家", 40005);
    }
}
