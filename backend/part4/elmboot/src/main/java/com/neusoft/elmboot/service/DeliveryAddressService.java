package com.neusoft.elmboot.service;

import java.util.List;
import com.neusoft.elmboot.po.DeliveryAddress;

public interface DeliveryAddressService {
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
    public DeliveryAddress getDeliveryAddressById(Integer daId);
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
    public int removeDeliveryAddress(Integer daId);
}