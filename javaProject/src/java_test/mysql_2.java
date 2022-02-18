package java_test;

import java.sql.DriverManager;
import java.sql.SQLException;

public class mysql_2 extends mysql{

		public void today(String id, int month, int day, int today_sale, int today_gita) {
			String query = "select month from "+id+"_sale where month = "+month+";";
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();        
				super.rs = stmt.executeQuery(query);
				System.out.println(query);
				
				if(rs.next()){
					if(rs!=null) {
						System.out.println(rs.getString("month"));
						Insert(id,month,day,today_sale,today_gita);
					}
				}
				else {
					tableInsert(id,month,day,today_sale,today_gita);
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
		public void tableInsert(String id, int month, int day, int today_sale, int today_expenses){ 
			String query = "INSERT INTO "+id+"_sale(month, sales, today, today_sale, expenses, today_expenses)values("+month+", "+today_sale+", "+day+", "+today_sale+", "+today_expenses+", "+today_expenses+");"; 
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
		public void Insert(String id, int month, int day, int today_sale, int today_expenses){ 
			String query = "select sales, today_sale, expenses, today_expenses from "+id+"_sale where today = "+day+" AND month = "+month+";";
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				if(rs.next()){
					if(rs!=null) {
						int sales = rs.getInt("sales");
						int wasSale = rs.getInt("today_sale");
						int expenses = rs.getInt("expenses");
						int today_expenses1 = rs.getInt("today_expenses");
						expenses -= today_expenses1;
						expenses += today_expenses;
						sales -= wasSale;
						sales += today_sale;
						changeInsert(id, month, sales, day, today_sale, expenses, today_expenses);
					}
				}
					else {
						System.out.println("-------");
						Today_sale(id, month, day, today_sale, today_expenses);
					}
				stmt.close(); 
				stmt.close(); 
				conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection");
					
				} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
				}
		} 
		public void changeInsert(String id, int month, int sales, int day, int today_sale, int expenses, int today_expenses){ 
			System.out.println("-------"); 
			String query = "UPDATE "+id+"_sale SET sales = "+sales+", today = "+day+", today_sale = "+today_sale+", expenses = "+expenses+", today_expenses ="+today_expenses+" where month = "+month+";"; 
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
		public void Today_sale(String id, int month, int day, int today_sale, int today_expenses){ 
			int day1 = day;
			String query = "select sales, expenses from "+id+"_sale where month = "+month+";";
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null) {
						System.out.println("1111111111");
						int sales = rs.getInt("sales");
						sales += today_sale;
						int expenses1= rs.getInt("expenses");
						expenses1 += today_expenses;
						changeInsert(id, month, sales, day1, today_sale, expenses1, today_expenses);
					}
				} 
				stmt.close(); 
				stmt.close(); 
				conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection");
					
				} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
				}
		} 
		

}
