package com.neusoft.elm.util;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSourceFactory;
public class DBUtil {
	private static DataSource dataSource;
	
	static{
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("C:\\Users\\some in gloss\\eclipse-workspace\\elm\\src\\main\\java\\com\\neusoft\\elm\\util\\dbcp.properties")));
            //读取项目根目录下的配置文件
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
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
