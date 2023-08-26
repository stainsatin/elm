package com.neusoft.elm.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.neusoft.elm.dao.CartDao;
import com.neusoft.elm.po.Cart;
import com.neusoft.elm.po.Food;
import com.neusoft.elm.util.DBUtil;
public class CartDaoImpl implements CartDao {
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	@Override
	public int saveCart(Cart cart)throws Exception{
		int result = 0;
		String sql = "insert into cart values(null,?,?,?,1)";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, cart.getFoodId());
			pst.setInt(2, cart.getBusinessId());
			pst.setString(3, cart.getUserId());
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}
	@Override
	public int updateCart(Cart cart)throws Exception{
		int result = 0;
		String sql = "update cart set quantity=? where userId=? and businessId=? and foodId=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, cart.getQuantity());
			pst.setString(2, cart.getUserId());
			pst.setInt(3, cart.getBusinessId());
			pst.setInt(4,cart.getFoodId());
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}	
	
	@Override
	public int removeCart(Cart cart)throws Exception{
		int result = 0;
		StringBuffer sql = new StringBuffer("delete from cart where userId=? and  businessId=?");
		if(cart.getFoodId()!=null) {
			sql.append(" and foodId="+cart.getFoodId());
		}
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setInt(1, cart.getFoodId());
			pst.setInt(2, cart.getBusinessId());
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}
}
