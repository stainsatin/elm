package com.neusoft.elm.dao;

import java.util.List;
import com.neusoft.elm.po.Business;
public interface BusinessDao {
	public List<Business> listBusinessByOrderTypeID(Integer orderTypeId)throws Exception;
	public Business getBusinessById(Integer businessId)throws Exception;
	public List<Business> listBusinessByName(String businessName)throws Exception;
	public List<Business> listBusinessByAddress(String businessAddress)throws Exception;
}
