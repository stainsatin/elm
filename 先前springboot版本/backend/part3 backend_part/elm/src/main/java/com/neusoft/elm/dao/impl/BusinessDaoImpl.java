package com.neusoft.elm.dao.impl;
import java.util.List;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.DBUtil;
import com.neusoft.elm.dao.BusinessDao;
import java.sql.*;
import java.util.ArrayList;
public class BusinessDaoImpl implements BusinessDao{
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
	public List<Business> listBusinessByOrderTypeID(Integer orderTypeId)throws Exception{
		List<Business>list = new ArrayList<>();
		String sql = "select * from business where orderTypeID = ? order by businessId";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderTypeId);
			rs = pst.executeQuery();
			while(rs.next()) {
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setRemarks(rs.getString("remarks"));
				business.setOrderTypeId((rs.getInt("orderTypeId")));
				list.add(business);
			}
		}finally {
			DBUtil.close(rs,pst);
		}
		return list;
	}
	@Override
	public Business getBusinessById(Integer businessId)throws Exception{
		Business business = null;
		String sql = "select * from business where businessId = ?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, businessId);
			rs = pst.executeQuery();
			if(rs.next()) {
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setRemarks(rs.getString("remarks"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
			}
		}finally {
			DBUtil.close(rs,pst);
		}
		return business;
	}
	
	@Override
	public List<Business> listBusinessByName(String businessName)throws Exception{
		List<Business> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from business where 1=1 ");
		if(businessName!=null) {
			sql.append(" and businessName like '%"+businessName+"%' ");
		}
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setRemarks(rs.getString("remarks"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
				list.add(business);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst);
		}
		return list;
	}
	
	@Override
	public List<Business> listBusinessByAddress(String businessAddress)throws Exception{
		List<Business> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from business where 1=1 ");
		if(businessAddress!=null) {
			sql.append(" and businessAddress like '%"+businessAddress+"%' ");
		}
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setRemarks(rs.getString("remarks"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
				list.add(business);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst);
		}
		return list;
	}
}
