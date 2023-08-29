package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.neusoft.elm.dao.OrderDetailetDao;
import com.neusoft.elm.po.Food;
import com.neusoft.elm.po.OrderDetailet;
import com.neusoft.elm.util.DBUtil;
import java.util.ArrayList;
public class OrderDetailetDaoImpl implements OrderDetailetDao {
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
	public int saveOrderDetailetBatch(List<OrderDetailet> list)throws Exception{
		int result = 0;
		StringBuffer sqlbuf = new StringBuffer("insert into orderDetailet(orderId,foodId,quantity) values");
		for(OrderDetailet od: list) {
			sqlbuf.append(" ("+ od.getOrderId()+","+od.getFoodId()+","+od.getQuantity()+"),");
		}
		//去掉SQL最后的逗号
		String sql = sqlbuf.toString().substring(0,sqlbuf.toString().length()-1);
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}
	
	@Override
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId)throws Exception{
		List<OrderDetailet> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select o.*,");
		sql.append(" f.foodId ffoodId,");
		sql.append(" f.foodName ffoodName,");
		sql.append(" f.foodPrice ffoodPrice");
		sql.append(" from OrderDetailet o left join food f on o.foodId=f.foodId");
		sql.append(" where o.orderId=?");
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				OrderDetailet od = new OrderDetailet();
				od.setOdId(rs.getInt("odId"));
				od.setOrderId(rs.getInt("orderId"));
				od.setFoodId(rs.getInt("foodId"));
				od.setQuantity(rs.getInt("quantity"));
				Food food = new Food();
				food.setFoodId(rs.getInt("ffoodId"));
				food.setFoodName(rs.getString("ffoodName"));
				food.setFoodPrice(rs.getDouble("ffoodPrice"));
				od.setFood(food);
				list.add(od);
			}
		}finally {
			DBUtil.close(rs,pst);
		}
		return list;
	}
}
