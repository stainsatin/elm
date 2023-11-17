package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.entity.Business;
import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	 private BusinessMapper businessMapper;

	@Override
	 public List<Business> listBusinessByOrderTypeId(Integer orderTypeId){
		 return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	 }
	 
	@Override
	 public Business getBusinessById(Integer businessId) {
		return businessMapper.getBusinessById(businessId);
	}
	
	@Override
	 public List<Business> listBusinessByName(String businessName){
		return businessMapper.listBusinessByName(businessName);
	 }
	
	@Override
	 public List<Business> listBusinessByAddress(String businessAddress){
		return businessMapper.listBusinessByAddress(businessAddress);
	}


	@Override
	public Integer updateBusinessHot(Integer businessId, Integer hot) {
		return businessMapper.updateBusinessHot(businessId, hot);
	}

	@Override
	public List<Business> listBusinessByOrderTypeIdByHot(Integer orderTypeId){
		return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	}

}
