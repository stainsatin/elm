package com.neusoft.elmboot.controller;


import com.neusoft.elmboot.bo.Result;
import com.neusoft.elmboot.entity.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryAddressController {
	@Resource
	private DeliveryAddressService deliveryAddressService;

	@GetMapping("/user-id")
	public Result listDeliveryAddressByUserId(String userId) {
		return Result.success(deliveryAddressService.listDeliveryAddressByUserId(userId));
	}


	@PostMapping
	public Result saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		return Result.success(deliveryAddressService.saveDeliveryAddress(deliveryAddress));
	}

	@GetMapping("/da-id")
	public Result getDeliveryAddressById(Integer daId) {
		return Result.success(deliveryAddressService.getDeliveryAddressById(daId));
	}

	@PutMapping
	public Result updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		return Result.success(deliveryAddressService.updateDeliveryAddress(deliveryAddress));
	}

	@DeleteMapping
	public Result removeDeliveryAddress(Integer daId) {
		return Result.success(deliveryAddressService.removeDeliveryAddress(daId));
	}
}
