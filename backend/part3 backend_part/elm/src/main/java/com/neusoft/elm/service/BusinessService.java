package com.neusoft.elm.service;
import com.neusoft.elm.po.Business;

import java.util.List;


public interface BusinessService {
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
    public Business getBusinessById(Integer businessId);
    public List<Business> listBusinessByName(String businessName);
    public List<Business> listBusinessByAddress(String businessAddress);
}
