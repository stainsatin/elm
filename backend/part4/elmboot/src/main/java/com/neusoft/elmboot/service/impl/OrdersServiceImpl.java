package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.entity.Cart;
import com.neusoft.elmboot.entity.Orders;
import com.neusoft.elmboot.exception.order.BusinessInOrderNotFoundException;
import com.neusoft.elmboot.exception.order.DeliveryAddressInOrderNotFoundException;
import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.DeliveryAddressMapper;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.util.CommonUtil;
import com.neusoft.elmboot.util.UserUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Resource
	private CartMapper cartMapper;
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private DeliveryAddressMapper deliveryAddressMapper;

	@Caching(evict = {@CacheEvict(cacheNames = "ordersList", allEntries = true)})
	@Override
	@Transactional
	public int createOrders(Integer businessId, Integer daId, double orderTotal) throws BusinessInOrderNotFoundException, DeliveryAddressInOrderNotFoundException {
		String userId = UserUtil.getUserId();
		List<Cart> cartList = cartMapper.getCartByUserId(userId);

		DeliveryAddress da = deliveryAddressMapper.getDeliveryAddressById(daId);
		if (da == null) throw new DeliveryAddressInOrderNotFoundException();

		Orders orders = new Orders(
				null,
				userId,
				businessId,
				CommonUtil.getCurrentDate(),
				orderTotal,
				daId,
				0
		);
		ordersMapper.saveOrders(orders);
		AtomicReference<Boolean> hasFound = new AtomicReference<>(false);
		cartList.forEach(
				cart -> {
					if (cart.getBusinessId().equals(businessId)) {
						hasFound.set(true);
						cartMapper.removeCart(cart);
					}
				});
		if (!hasFound.get()) {
			throw new BusinessInOrderNotFoundException();
		}
		return orders.getOrderId();
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
