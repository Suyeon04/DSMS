package java_test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class mysql_3 extends mysql{

	public int[] bringItem(String id, int month) {
		// TODO Auto-generated method stub
		String query = "select sales,expenses from "+id+"_sale where month = "+month+";";
		int[] a = new int[2];
		try {
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
			super.stmt = conn.createStatement();
			super.rs = stmt.executeQuery(query);
			System.out.println(query);
			while (rs.next()){ 
				if(rs!=null) {
					a[0] = rs.getInt("sales");
					a[1] = rs.getInt("expenses");
				}
			} 
			stmt.close();
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection");
			} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
			}
		return a;
	}
	
	public int bringGraph(String id, int year, int num) {
		// TODO Auto-generated method stub
		year = year*100+num;
		String query = "select sales from "+id+"_sale where month = "+year+";";
		try {
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
			super.stmt = conn.createStatement();
			super.rs = stmt.executeQuery(query);
			System.out.println(query);
			while (rs.next()){ 
				if(rs!=null) {
					return rs.getInt("sales");
				}
			} 
			stmt.close();
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection");
			} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
			}
		return 0;
	}
	
	public int[] bringItem2(String id, int month, int day) {
		// TODO Auto-generated method stub
		String query = "select today_sale, today_expenses from "+id+"_sale where month = "+month+" and today = "+day+";";
		int[] a = new int[2];
		System.out.println(query);
		try {
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
			super.stmt = conn.createStatement();
			super.rs = stmt.executeQuery(query);
			while (rs.next()){ 
				if(rs!=null) {
					a[0] = rs.getInt("today_sale");
					a[1] = rs.getInt("today_expenses");
				}
			} 
			stmt.close();
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection");
			} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
			}
		return a;
	}
	
}
