package com.neusoft.elmboot.service;

import com.neusoft.elmboot.entity.Cart;
import com.neusoft.elmboot.exception.cart.FoodIdBusinessIdNotMatchException;

import java.util.List;

public interface CartService {
    public int saveCart(Integer businessId, Integer foodId);

    public String removeCart(Integer businessId, Integer foodId) throws FoodIdBusinessIdNotMatchException;

    public List<Cart> getCartByUserId(String userId);
}