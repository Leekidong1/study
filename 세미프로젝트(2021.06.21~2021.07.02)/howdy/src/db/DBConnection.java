package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {	// DB를 가져오는 클래스
	
	public static void initConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	// 준비작업 : 오라클 연결하는 클래스 불러옴.
			
			System.out.println("Driver Loading Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");	// 오라클과 연결
			
			System.out.println("Oracle Connection Success!");
		} catch (SQLException e) {
			System.out.println("DB를 연결하지 못했습니다");
		}
		
		return conn;
	}
}
