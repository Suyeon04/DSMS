package java_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // jdbc 靛扼捞滚 林家 
	static final String DB_URL = "jdbc:mysql://localhost:3306/DSMS?useSSL=false"; // DB 立加 林家 
	static final String USERNAME = "root"; // DB ID 
	static final String PASSWORD = "mirim"; // DB Password 
	
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null; 
		
	
	public mysql(){ // DB 立加 
		System.out.print("DatabaseName 单捞磐海捞胶 立加 : "); 
		try { 
			Class.forName(JDBC_DRIVER); 
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			if (conn != null){
				System.out.println("己傍");
			} else{System.out.println("角菩");} 
		} catch (ClassNotFoundException e) { 
			System.out.println("Class Not Found Exection"); 
			e.printStackTrace(); 
			} catch (SQLException e) { 
				System.out.println("SQL Exception"); 
				e.printStackTrace(); 
				} 
		}//UserDAO

}
