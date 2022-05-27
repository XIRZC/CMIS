package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
 
import java.sql.Statement;
import java.sql.PreparedStatement;
 
public class DBUtil {
	/**
	 * 在成员位置定义5个存储数据库信息的变量
	 */
 
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/cmis?useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "xzc0814..";
 
	/**
	 * 定义一个Connection类型的变量用来存储获取到的Connection实例化对象
	 */
	private static Connection conn;
 
	/**
	 * 私有构造方法，防止用户创建对象，浪费内存空间
	 */
	private DBUtil() {
 
	}
 
	public static Connection getConnection() {
 
		try {
			if (conn == null || conn.isClosed()) {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
 
	}
 
	/**
	 * 定义一个静态方法,用于释放资源
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//关闭使用preparedStatement的连接过程
	public static void close(ResultSet rs, PreparedStatement stat, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 
}