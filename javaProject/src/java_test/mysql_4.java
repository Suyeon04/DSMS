package java_test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;

public class mysql_4 extends mysql{
	public void today(String id, int day, int today_sale, int today_expenses, int today_total) {
		String query = "select day from "+id+"_today where day = "+day+";";
		try {
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
			super.stmt = conn.createStatement();        
			super.rs = stmt.executeQuery(query);
			System.out.println(query);
			
			if(rs.next()){
				if(rs!=null) {
					UpdateToday(id, day, today_sale, today_expenses, today_total);
				}
			}
			rs.close();
			stmt.close(); 
			stmt.close(); 
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection");
				
			} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
					
			}
		
    }
	
	protected void UpdateToday(String id, int day, int today_sale, int today_expenses, int today_total) {
		// TODO Auto-generated method stub
		System.out.println("-------"); 
		String query = "UPDATE "+id+"_today SET today_sale = "+today_sale+", today_expenses = "+today_expenses+", today_total = "+today_total+" where day = "+day+";"; 
		System.out.println(query); // Qurey문 확인 
		try { // 데이터베이스에 접속합니다. 
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			super.stmt = conn.createStatement(); //데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
			super.stmt.executeUpdate(query); // query를 실행시킵니다.
		stmt.close(); 
		conn.close(); 
		} catch (ClassNotFoundException e) { 
			System.out.println("Class Not Found Exection"); 
			} catch (SQLException e) { 
				System.out.println("SQL Exception : " + e.getMessage()); 
			} 
	}
	
	public int[] showToday(String id, int day) {
		String query = "select today_sale, today_expenses, today_total from "+id+"_today where day = "+day+";";
		int a[] = new int[3];
		try {
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
			super.stmt = conn.createStatement();        
			super.rs = stmt.executeQuery(query);
			System.out.println(query);
			if(rs.next()){
				if(rs!=null) {
					a[0] = rs.getInt("today_sale");
					a[1] = rs.getInt("today_expenses");
					a[2] = rs.getInt("today_total");
				}
			}
			rs.close();
			stmt.close(); 
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
