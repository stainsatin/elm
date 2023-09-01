package com.neusoft.elmboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;
@RestController
@RequestMapping("/OrdersController")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("/createOrders")
	public int createOrders(Orders orders)throws Exception{
		return ordersService.createOrders(orders);
	}
	@RequestMapping("/getOrdersById")
    public Orders getOrdersById(Integer orderId)throws Exception{
    	return ordersService.getOrdersById(orderId);
    }
	@RequestMapping("/listOrdersByUserId")
    public List<Orders> listOrdersByUserId(String userId)throws Exception{
    	return ordersService.listOrdersByUserId(userId);
    }
}
