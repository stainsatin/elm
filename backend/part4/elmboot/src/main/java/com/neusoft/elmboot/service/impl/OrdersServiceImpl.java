package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.dto.OrderDetailet;
import com.neusoft.elmboot.entity.Cart;
import com.neusoft.elmboot.entity.Orders;
import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.OrderDetailetMapper;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	private CartMapper cartMapper;	
	@Autowired
	private OrdersMapper ordersMapper;	
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;

	@Caching(evict = {@CacheEvict(cacheNames = "ordersList", allEntries = true)})
	@Override
	@Transactional
	public int createOrders(Integer businessId, Integer daId, double orderTotal) {
		//查询当前购物车当前商家所有食品
		Cart cart = new Cart();
		String userId = UserUtil.getUserId();
		cart.setUserId(userId);
		cart.setBusinessId(businessId);
		List<Cart> cartList = cartMapper.getCartByUserId("");

		Orders orders = new Orders(
				null,
				userId,
				businessId,
				CommonUtil.getCurrentDate(),
				orderTotal,
				daId,
				0, null
		);
		//创建订单
		ordersMapper.saveOrders(orders);
		int orderId = orders.getOrderId();
		System.out.println(orderId);
		System.out.println("123");
		List<OrderDetailet> list = new ArrayList<>();
		for (Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			list.add(od);
		}
		orderDetailetMapper.saveOrderDetailetBatch(list);
	
		//remove cart
		cartMapper.removeCart(cart);
		
		return orderId;
	}
	
	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}
	
	@Override
	public List<Orders> listOrdersByUserId(String userId){
		return ordersMapper.listOrdersByUserId(userId);
	}
}
