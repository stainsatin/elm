package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.BusinessMapper;
import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.BusinessService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	 private BusinessMapper businessMapper;
	
	@Cacheable(value = "BusinessList",key = "#orderTypeId")
	@Override
	 public List<Business> listBusinessByOrderTypeId(Integer orderTypeId){
		 return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	 }
	 
	@Cacheable(value = "Business")
	@Override
	 public Business getBusinessById(Integer businessId) {
		return businessMapper.getBusinessById(businessId);
	}
	
	@Cacheable(value = "BusinessList",key = "#businessName")
	@Override
	 public List<Business> listBusinessByName(String businessName){
		return businessMapper.listBusinessByName(businessName);
	 }
	
	@Cacheable(value = "BusinessList",key = "#businessAddress")
	@Override
	 public List<Business> listBusinessByAddress(String businessAddress){
		return businessMapper.listBusinessByAddress(businessAddress);
	}
	
	@Caching(evict = {@CacheEvict(value = "Business",allEntries = true)},
            put = {@CachePut(value = "BusinessList",key = "#businessId")})
	@Override
	public Integer updateBusinessHot(Integer businessId, Integer hot) {
		return businessMapper.updateBusinessHot(businessId, hot);
	}

	@Cacheable(value = "BusinessList",key = "#orderTypeId")
	@Override
	public List<Business> listBusinessByOrderTypeIdByHot(Integer orderTypeId){
		return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	}

}
