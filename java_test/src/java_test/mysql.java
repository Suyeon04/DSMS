package java_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // jdbc ����̹� �ּ� 
	static final String DB_URL = "jdbc:mysql://localhost:3306/DSMS?useSSL=false"; // DB ���� �ּ� 
	static final String USERNAME = "root"; // DB ID 
	static final String PASSWORD = "mirim"; // DB Password 
	
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null; 
		
	
	public mysql(){ // DB ���� 
		System.out.print("DatabaseName �����ͺ��̽� ���� : "); 
		try { 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			if (conn != null){
				System.out.println("����");
			} else{System.out.println("����");} 
		} catch (ClassNotFoundException e) { 
			System.out.println("Class Not Found Exection"); 
			e.printStackTrace(); 
			} catch (SQLException e) { 
				System.out.println("SQL Exception"); 
				e.printStackTrace(); 
				} 
		}//UserDAO

}
