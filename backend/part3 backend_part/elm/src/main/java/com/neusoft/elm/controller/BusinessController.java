package com.neusoft.elm.controller;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusinessController {
    public Object listBusinessOrderTypeID(HttpServletRequest request)throws Exception{
        Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
        BusinessService service = new BusinessServiceImpl();
        List<Business> list= service.listBusinessByOrderTypeId(orderTypeId);
        return list;
    }
    public Object getBusinessById(HttpServletRequest request)throws Exception
    {
        Integer businessId = Integer.valueOf(request.getParameter("businessId"));
        Business business = new BusinessServiceImpl().getBusinessById(businessId);
        return business;
    }
}
