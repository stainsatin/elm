package com.neusoft.elmboot.service;

import com.neusoft.elmboot.entity.Orders;
import com.neusoft.elmboot.exception.order.BusinessInOrderNotFoundException;
import com.neusoft.elmboot.exception.order.DeliveryAddressInOrderNotFoundException;
import com.neusoft.elmboot.exception.order.OrderHasPayedException;
import com.neusoft.elmboot.exception.wallet.BalanceRemainNotEnoughException;
import com.neusoft.elmboot.exception.wallet.PayOrdersFailedException;
import com.neusoft.elmboot.exception.wallet.UserHasNotCreatedWalletIdException;

import java.util.List;

public interface OrdersService {
    public int createOrders(Integer businessId, Integer daId, double orderTotal) throws BusinessInOrderNotFoundException, DeliveryAddressInOrderNotFoundException;

    public Orders getOrdersById(Integer orderId);

    public List<Orders> listOrdersByUserId(String userId);

    public String payOrders(Integer orderId) throws BalanceRemainNotEnoughException, UserHasNotCreatedWalletIdException, PayOrdersFailedException, OrderHasPayedException;

}