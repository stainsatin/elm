package com.neusoft.elm.controller;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusinessController {
	//根据食品类别列出商家
    public Object listBusinessOrderTypeID(HttpServletRequest request)throws Exception{
        Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
        BusinessService service = new BusinessServiceImpl();
        List<Business> list= service.listBusinessByOrderTypeId(orderTypeId);
        return list;
    }
    //根据商家编号列出商家
    public Object getBusinessById(HttpServletRequest request)throws Exception
    {
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        Business business = new BusinessServiceImpl().getBusinessById(businessId);
        return business;
    }
    
    //根据商家名称列出商家
    public Object listBusinessByName(HttpServletRequest request)throws Exception{
    	String businessName = request.getParameter("businessName");
    	BusinessService service = new BusinessServiceImpl();
    	List<Business> list= service.listBusinessByName(businessName);
    	return list;
    }
    
  //根据商家地址列出商家
    public Object listBusinessByAddress(HttpServletRequest request)throws Exception{
    	String businessAddress = request.getParameter("businessAddress");
    	BusinessService service = new BusinessServiceImpl();
    	List<Business> list= service.listBusinessByAddress(businessAddress);
    	return list;
    }
}
