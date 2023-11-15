package com.neusoft.elmboot.exception.cart;

import com.neusoft.elmboot.exception.BaseException;

public class FoodIdBusinessIdNotMatchException extends BaseException {
    public FoodIdBusinessIdNotMatchException() {
        super("未找到该商品", 40004);
    }
}
