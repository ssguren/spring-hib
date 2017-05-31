package com.his.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.persistence.Entity;

@Entity
public class JDBCUtil {
	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";

	// ͨ��properties�ļ�������ݿ�����
	static {
		try {
			// Properties prop = new Properties();
			// // �õ������ļ���������
			// // JDBCUtils.class �ֽ���
			// // getClassLoader(): �������--> classpath�ĸ�Ŀ¼
			// InputStream in = JDBCUtils.class.getClassLoader()
			// .getResourceAsStream("db.properties");
			// prop.load(in);
			//

			Properties prop = PropertiesUtil.getProperties("db.properties");
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");

			Class.forName(driver);
		} catch (Exception ex) {
			throw new ExceptionInInitializerError(ex);
		}

	}

	// ��ȡ����
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

	public static void main(String[] args) {

	}
}