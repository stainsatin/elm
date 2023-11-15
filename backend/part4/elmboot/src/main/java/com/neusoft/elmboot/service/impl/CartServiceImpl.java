package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.entity.Cart;
import com.neusoft.elmboot.exception.cart.FoodIdBusinessIdNotMatchException;
import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.FoodMapper;
import com.neusoft.elmboot.service.CartService;
import com.neusoft.elmboot.util.UserUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
	@Resource
	private CartMapper cartMapper;
	private FoodMapper foodMapper;
	private BusinessMapper businessMapper;

	@Override
	public int saveCart(Integer businessId, Integer foodId) {
		Cart cart = new Cart();
		cart.setBusinessId(businessId);
		cart.setFoodId(foodId);
		cart.setUserId(UserUtil.getUserId());
		Cart newCart = cartMapper.getCart(foodId, businessId, UserUtil.getUserId());
		if (newCart != null) {
			cart.setQuantity(newCart.getQuantity() + 1);
			return cartMapper.updateCart(cart);
		} else {
			cart.setQuantity(1);
			return cartMapper.saveCart(cart);
		}
	}


	@Override
	public String removeCart(Integer businessId, Integer foodId) throws FoodIdBusinessIdNotMatchException {
		Cart newCart = cartMapper.getCart(foodId, businessId, UserUtil.getUserId());
		if (newCart == null) {
			throw new FoodIdBusinessIdNotMatchException();
		} else {
			Integer quantity = newCart.getQuantity();
			if (quantity <= 1) {
				cartMapper.removeCart(newCart);
			} else {
				newCart.setQuantity(quantity - 1);
				cartMapper.updateCart(newCart);
			}
			return "success";
		}
	}

	@Override
	public List<Cart> getCartByUserId(String userId) {
		return cartMapper.getCartByUserId(userId);
	}

}