package com.neusoft.elm.service.impl;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.dao.CartDao;
import com.neusoft.elm.dao.OrdersDao;
import com.neusoft.elm.dao.impl.OrdersDaoImpl;
import com.neusoft.elm.dao.impl.CartDaoImpl;
import com.neusoft.elm.dao.UserDao;
import com.neusoft.elm.dao.impl.UserDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.po.Cart;
import com.neusoft.elm.po.OrderDetailet;
import com.neusoft.elm.po.Orders;
import com.neusoft.elm.service.OrdersService;
import com.neusoft.elm.util.DBUtil;
import com.neusoft.elm.dao.OrderDetailetDao;
import com.neusoft.elm.dao.impl.OrderDetailetDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    @Override
    public int createOrders(String userId, Integer businessId, Integer daId, Double orderTotal) {
    	UserDao userDao = new UserDaoImpl();
    	int res1 = 0;
    	try {
    		DBUtil.getConnection();
			res1 = userDao.getUserById(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
    	if(res1==0)
    		return 0;
    	
    	BusinessDao bdao = new BusinessDaoImpl();
    	Business res2 = null;
    	try {
    		DBUtil.getConnection();
			res2 = bdao.getBusinessById(businessId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
    	if(res2==null)
    		return 0;
    	
    	int orderId = 0;
        CartDao cartDao = new CartDaoImpl();
        OrdersDao ordersDao = new OrdersDaoImpl();
        OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
        try {
            DBUtil.beginTransaction();
            
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setBusinessId(businessId);
            List<Cart> cartList = cartDao.listCart(cart);
            
            Orders orders = new Orders();
            orders.setUserId(userId);
            orders.setBusinessId(businessId);
            orders.setDaId(daId);
            orders.setOrderTotal(orderTotal);
            orderId = ordersDao.saveOrders(orders);
            
            List<OrderDetailet> orderDetailetList = new ArrayList();
            for(Cart c:cartList) {
            	OrderDetailet od = new OrderDetailet();
            	od.setOrderId(orderId);
            	od.setFoodId(c.getFoodId());
            	od.setQuantity(c.getQuantity());
            	orderDetailetList.add(od);
            }
            orderDetailetDao.saveOrderDetailetBatch(orderDetailetList);
            
            //清空购物车
            cartDao.removeCart(cart);
            
            DBUtil.commitTransaction();
        }catch (Exception e) {
        	orderId = 0;
        	try {
        		DBUtil.rollbackTransaction();
        	}catch(Exception e1) {
        		e1.printStackTrace();
        	}
        	e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return orderId;
    }

    @Override
    public Orders getOrdersById(Integer orderId) {
    	Orders  orders = null; 
    	OrdersDao ordersDao = new OrdersDaoImpl();
        OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
        try {
        	DBUtil.getConnection();
            //根据订单ID查询订单信息
            orders = ordersDao.getOrdersById(orderId);
            //查订单明细
            List<OrderDetailet> list = orderDetailetDao.listOrderDetailetByOrderId(orderId);
            orders.setList(list);
        }catch (Exception e) {
        	e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return orders;
    }

    @Override
    public List<Orders> listOrdersByUserId(String userId) {
    	List<Orders> list = new ArrayList<>();
    	OrdersDao ordersDao = new OrdersDaoImpl();
        OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
        try {
        	DBUtil.getConnection();
           list = ordersDao.listOrdersByUserId(userId);
            //查订单明细
            for(Orders o:list) {
            	List<OrderDetailet> odlist = orderDetailetDao.listOrderDetailetByOrderId(o.getOrderId());
            	o.setList(odlist);
            }
        }catch (Exception e) {
        	e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return list;
    }
}
