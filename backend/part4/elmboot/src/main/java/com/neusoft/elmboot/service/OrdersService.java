package com.neusoft.elmboot.service;

import com.neusoft.elmboot.entity.Orders;

import java.util.List;
public interface OrdersService {
    public int createOrders(Integer businessId, Integer daId, double orderTotal);

    public Orders getOrdersById(Integer orderId);
    public List<Orders> listOrdersByUserId(String userId);
}