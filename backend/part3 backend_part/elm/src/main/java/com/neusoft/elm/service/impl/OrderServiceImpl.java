package com.neusoft.elm.service.impl;

import com.neusoft.elm.po.Orders;
import com.neusoft.elm.service.OrdersService;

import java.util.List;

public class OrderServiceImpl implements OrdersService {
    @Override
    public int createOrders(String userId, Integer businessId, Integer daId, Double orderTotal) {
        return 0;
    }

    @Override
    public Orders getOrdersById(Integer orderId) {
        return null;
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId) {
        return null;
    }
}
