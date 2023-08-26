package com.neusoft.elm.util;
import java.sql.*;
public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/elm?characterEncoding=utf-8";
	private static final String USERNAME = "ydt";
	private static final String PASSWORD = "123";
	private static final String Driver = "com.mysql.jdbc.Driver";
	
	private static final ThreadLocal<Connection> TL = new ThreadLocal<>();
	
	public static Connection getConnection() {
		Connection con = null;
		con = TL.get();
		if(con==null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}
	
	private static Connection createConnection() {
		 Connection connection = null;
		 try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
		    connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		 }catch (Exception e){
		    e.printStackTrace();
		 }
		 return connection;
	}
	
	//开启事务
	public static void beginTransaction() throws Exception{
		Connection con = null;
		con = TL.get();
		if(con==null) {
			con = createConnection();
			TL.set(con);
		}
		con.setAutoCommit(false);
	}
	//提交事务
	public static void commitTransaction()throws Exception{
		Connection con = TL.get();
		con.commit();
	}
	
	//回滚一个事务
	public static void rollbackTransaction()throws Exception{
		Connection con = TL.get();
		con.rollback();
	}
	
	public static void close(ResultSet rs,PreparedStatement pst){
	    if (rs!=null){
	        try {
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    if (pst!=null){
	        try {
	            pst.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void close(PreparedStatement pst){
	    if (pst!=null){
	        try {
	            pst.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static void close(ResultSet rs,PreparedStatement pst,Connection con){
	    if (rs!=null){
	        try {
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    if (pst!=null){
	        try {
	            pst.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    if (con!=null){
	        try {
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void close() {
		Connection con = TL.get();
		try {
			if(con!=null) {
				con.close();
			}
			//必须要remove，不然容易造成内存泄漏
			TL.remove();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
