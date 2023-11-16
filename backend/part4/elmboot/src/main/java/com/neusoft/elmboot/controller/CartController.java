package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.bo.Result;
import com.neusoft.elmboot.entity.Cart;
import com.neusoft.elmboot.exception.cart.FoodIdBusinessIdNotMatchException;
import com.neusoft.elmboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping
	public List<Cart> getCartByUserId(String userId) {
		return cartService.getCartByUserId(userId);
	}

	@PostMapping
	public Result saveCart(Integer businessId, Integer foodId) {
		return Result.success(cartService.saveCart(businessId, foodId));
	}

	@DeleteMapping
	public Result removeCart(Integer businessId, Integer foodId) throws FoodIdBusinessIdNotMatchException {
		return Result.success(cartService.removeCart(businessId, foodId));
	}
}
