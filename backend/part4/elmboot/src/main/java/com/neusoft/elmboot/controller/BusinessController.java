package com.neusoft.elmboot.controller;

import java.util.List;

import com.neusoft.elmboot.dto.Result;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.entity.Business;
import com.neusoft.elmboot.service.BusinessService;

@RestController
@RequestMapping("/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/order-type-id")
	public Result listBusinessByOrderTypeId(Integer orderTypeId)throws Exception{
		return Result.success(businessService.listBusinessByOrderTypeId(orderTypeId));
	}
	
	@GetMapping
	public Result getBusinessById(Integer businessId)throws Exception{
		return Result.success(businessService.getBusinessById(businessId));
	}
	
	@RequestMapping("/name")
	public Result listBusinessByName(String businessName)throws Exception{
		return Result.success(businessService.listBusinessByName(businessName));
	}
	
	@RequestMapping("/address")
	public Result listBusinessByAddress(String businessAddress){
		return Result.success(businessService.listBusinessByAddress(businessAddress));
	}

	@PutMapping("/hot")
	public Result updateBusinessHot(Integer businessId){
		Integer hot = businessService.getBusinessById(businessId).getHot();
		hot++;
		return Result.success(businessService.updateBusinessHot(businessId, hot));
	}

	@GetMapping("/hot")
	public Result listBusinessByOrderTypeIdByHot(Integer orderTypeId){
		return Result.success(businessService.listBusinessByOrderTypeIdByHot(orderTypeId));
	}

}
