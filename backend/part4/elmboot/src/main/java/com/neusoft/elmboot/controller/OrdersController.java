package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.dto.Result;
import com.neusoft.elmboot.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;

	@PostMapping
	public Result createOrders(Integer businessId, Integer daId, double orderTotal) throws Exception {
		return Result.success(ordersService.createOrders(businessId, daId, orderTotal));
	}

	@GetMapping
	public Result getOrdersById(Integer orderId) throws Exception {
		return Result.success(ordersService.getOrdersById(orderId));
	}

	@GetMapping("/user-id")
	public Result listOrdersByUserId(String userId) throws Exception {
		return Result.success(ordersService.listOrdersByUserId(userId));
	}
}
