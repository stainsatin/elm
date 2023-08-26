package com.neusoft.elm.util;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBUtil {
	private static DataSource dataSource;
	
	static{
	        try {
	            Properties properties = new Properties();
	            properties.load(new FileInputStream(new File("C:\\Users\\some in gloss\\Desktop\\elm_admin\\src\\com\\neusoft\\elm\\util\\dbcp.properties")));
	            //读取项目根目录下的配置文件
	            dataSource = BasicDataSourceFactory.createDataSource(properties);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//关闭资源
	public static void close(ResultSet rs,PreparedStatement pst,Connection con) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pst = null;
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
}
