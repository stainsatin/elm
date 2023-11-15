package com.neusoft.elmboot.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping
    public List<Cart> listCart(Cart cart)throws Exception{
		return cartService.listCart(cart);
	}
	
	@PostMapping
	public int saveCart(Cart cart)throws Exception{
		return cartService.saveCart(cart);
	}
	@PutMapping
    public int updateCart(Cart cart)throws Exception{
    	return cartService.updateCart(cart);
    }
	@RequestMapping
    public int removeCart(Cart cart)throws Exception{
    	return cartService.removeCart(cart);
    }
}
