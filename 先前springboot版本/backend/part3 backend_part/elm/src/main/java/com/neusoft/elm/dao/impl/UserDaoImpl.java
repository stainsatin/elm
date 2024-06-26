package com.neusoft.elm.dao.impl;
import com.neusoft.elm.dao.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.po.User;
import com.neusoft.elm.util.DBUtil;
public class UserDaoImpl implements UserDao{
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
	public User getUserByIdByPass(String userId,String password)throws Exception{
		User user = null;
		String sql = "select * from user where userId=? and password=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			String digestPass = DBUtil.hashPassword(password); 
			pst.setString(2, digestPass);
			rs = pst.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getInt("userSex"));
				user.setUserImg(rs.getString("userImg"));
				user.setDelTag(rs.getInt("delTag"));
			}
		}finally {
			DBUtil.close(rs,pst);
		}
		return user;
	}
	
	@Override
	public int getUserById(String userId)throws Exception{
		int result = 0;
		String sql = "select * from user where userId=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally {
			DBUtil.close(rs,pst);
		}
		return result;
	}
	
	@Override
	public int saveUser(User user)throws Exception{
		if(user==null)
			return 0;
		int result = 0;
		String sql = "insert into user values(?,?,?,?,?,1)";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getUserId());
			String pass = user.getPassword();
			String digest = DBUtil.hashPassword(pass);
			pst.setString(2, digest);
			pst.setString(3,user.getUserName());
			pst.setInt(4, user.getUserSex());
			pst.setString(5,user.getUserImg());
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}
	
	@Override
	public int updateUserMsg(String userId,String userName)throws Exception{
		int result = 0;
		StringBuffer sql = new StringBuffer("update user set userName = ? where userId = ?");
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			pst.setString(1, userName);
			pst.setString(2, userId);
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}
	
	@Override
	public int updateUserPassword(String userId,String oldPass,String newPass)throws Exception{
		int result = 0;
		String sql = "update user set password = ? where userId = ? and password = ?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, DBUtil.hashPassword(newPass));
			pst.setString(2, userId);
			pst.setString(3, DBUtil.hashPassword(oldPass));
			result = pst.executeUpdate();
		}finally {
			DBUtil.close(pst);
		}
		return result;
	}
}
