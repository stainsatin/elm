package com.neusoft.elmboot.service;
import com.neusoft.elmboot.po.Business;

import java.util.List;


public interface BusinessService {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
    public Business getBusinessById(Integer businessId);
    public List<Business> listBusinessByName(String businessName);
    public List<Business> listBusinessByAddress(String businessAddress);
    public Integer updateBusinessHot(Integer businessId,Integer hot);
    public List<Business> listBusinessByOrderTypeIdByHot(Integer orderTypeId);
}
