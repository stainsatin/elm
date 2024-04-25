package com.neusoft.elm.service.impl;

import com.neusoft.elm.dao.DeliveryAddressDao;
import com.neusoft.elm.dao.impl.DeliveryAddressDaoImpl;
import com.neusoft.elm.dao.UserDao;
import com.neusoft.elm.dao.impl.UserDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
    	List<DeliveryAddress> list = new ArrayList<>();
    	DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        try {
            DBUtil.getConnection();
            list = dao.listDeliveryAddressByUserId(userId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }

    @Override
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
    	UserDao udao = new UserDaoImpl();
    	int flag = 0;
    	try {
            DBUtil.getConnection();
			flag = udao.getUserById(deliveryAddress.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
    	if(flag == 0)
    		return 0;
    	
    	int result = 0;
    	DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.saveDeliveryAddress(deliveryAddress);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return result;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
    	DeliveryAddress deliveryAddress = new DeliveryAddress();
    	DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
    	try {
            DBUtil.getConnection();
            deliveryAddress = dao.getDeliveryAddressById(daId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return deliveryAddress;
    }

    @Override
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
    	UserDao udao = new UserDaoImpl();
    	int flag = 0;
    	try {
            DBUtil.getConnection();
			flag = udao.getUserById(deliveryAddress.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
    	if(flag == 0)
    		return 0;
    	
    	int result = 0;
    	DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.updateDeliveryAddress(deliveryAddress);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return result;
    }

    @Override
    public int removeDeliveryAddress(Integer daId) {
    	int result = 0;
    	DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
        try {
            DBUtil.getConnection();
            result = dao.removeDeliveryAddress(daId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return result;
    }
}
