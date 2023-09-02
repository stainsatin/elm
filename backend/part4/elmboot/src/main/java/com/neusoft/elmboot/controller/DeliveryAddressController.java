package com.neusoft.elmboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;

@RestController
@RequestMapping("/DeliveryAddressController")
public class DeliveryAddressController {
	@Autowired
	private DeliveryAddressService deliveryAddressService;
	
	@RequestMapping("/listDeliveryAddressByUserId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId)throws Exception{
		return deliveryAddressService.listDeliveryAddressByUserId(userId);
	}
	
	@RequestMapping("/saveDeliveryAddress")
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress)throws Exception{
    	return deliveryAddressService.saveDeliveryAddress(deliveryAddress);
    }
    
	@RequestMapping("/getDeliveryAddressById")
    public DeliveryAddress getDeliveryAddressById(Integer daId)throws Exception{
    	return deliveryAddressService.getDeliveryAddressById(daId);
    }
    
	@RequestMapping("/updateDeliveryAddress")
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress)throws Exception{
    	return deliveryAddressService.updateDeliveryAddress(deliveryAddress);
    }
    
	@RequestMapping("/removeDeliveryAddress")
    public int removeDeliveryAddress(Integer daId)throws Exception{
    	return deliveryAddressService.removeDeliveryAddress(daId);
    }
}
