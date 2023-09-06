package com.neusoft.elmboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.BusinessService;

@RestController
@RequestMapping("/BusinessController")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping("/listBusinessByOrderTypeId")
	public List<Business> listBusinessByOrderTypeId(Business business)throws Exception{
		return businessService.listBusinessByOrderTypeId(business.getOrderTypeId());
	}
	
	@RequestMapping("/getBusinessById")
	public Business getBusinessById(Integer businessId)throws Exception{
		return businessService.getBusinessById(businessId);
	}
	
	@RequestMapping("/listBusinessByName")
	public List<Business> listBusinessByName(String businessName)throws Exception{
		return businessService.listBusinessByName(businessName);
	}
	
	@RequestMapping("/listBusinessByAddress")
	public List<Business> listBusinessByAddress(String businessAddress){
		return businessService.listBusinessByAddress(businessAddress);
	}

	@RequestMapping("/updateBusinessHot")
	public Integer updateBusinessHot(Integer businessId){
		Integer hot = businessService.getBusinessById(businessId).getHot();
		hot++;
		return businessService.updateBusinessHot(businessId, hot);
	}

	@RequestMapping("/listBusinessByOrderTypeIdByHot")
	public List<Business> listBusinessByOrderTypeIdByHot(Integer orderTypeId){
		return businessService.listBusinessByOrderTypeIdByHot(orderTypeId);
	}

}
