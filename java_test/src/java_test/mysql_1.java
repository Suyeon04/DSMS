package java_test;

import java.sql.DriverManager; 
import java.sql.SQLException;  

public class mysql_1 extends mysql{
		
		public boolean select(String ID) {
			String query = "select ID from user where ID = '"+ID+"';";
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null)
						return false;
					} 
				stmt.close(); 
				conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection");
					return true;
				} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
						return true;
				}
			return true;
	    }
		public String bringName(String ID) {
			String query = "select name from user where ID = '"+ID+"';";
			TableName tn = new TableName();
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null) {
						tn.setName(rs.getString("name"));
						System.out.println(rs.getString("name"));
						return tn.getName();
					}
				} 
				stmt.close();
				conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection");
					return null;
				} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
						return null;
				}
			return null;
	    }
		public float bringMoney(String ID) {
			String query = "select money from user where ID = '"+ID+"';";
			TableName tn = new TableName();
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null) {
						tn.setMoney(rs.getFloat("money"));
						return tn.getMoney();
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
		public String[] bringItem(String ID) {
			String query = "select PW, brand from user where ID = '"+ID+"';";
			String[] a = new String[2];
			TableName tn = new TableName();
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null) {
						tn.setPW(rs.getString("PW"));
						tn.setBrand(rs.getString("brand"));
						a[0] = tn.getPW();
						a[1] = tn.getBrand();
						return a;
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
	    }public int bringEmp(String ID) {
			String query = "select employee from user where ID = '"+ID+"';";
			TableName tn = new TableName();
			try {
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null) {
						tn.setEmployee(rs.getInt("employee"));
						return tn.getEmployee();
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
		
		public String checkID(String ID, String PW) {
			String query = "select ID from user where ID = '"+ID+ "' and PW = '"+PW+"';"; // table�� ��� ���ڵ带 ����ϴ� query���Դϴ�. 
			try { // �����ͺ��̽��� �����մϴ�. 
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
				super.stmt = conn.createStatement();
				super.rs = stmt.executeQuery(query);
				while (rs.next()){ 
					if(rs!=null)
						return ID;
					} 
				stmt.close(); 
				conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection");
					return null;
				} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
						return null;
				}
			return null;
			} 

			
		public void tableInsert(String name, String ID, String PW, String brand, float money, int employee){ 
			System.out.println("-------");
			TableName tn = new TableName(name,ID,PW,brand, money, employee); //�ܺο��� �Ű������� �����͸� �޾� Qurey���� ����� �ݴϴ�. 
			String query = "INSERT INTO user(name, ID, PW, brand, money, employee)values('" + tn.getName() + "', '" + tn.getID() +"','"+tn.getPW() +"','"+tn.getBrand() +"',"+tn.getMoney() +","+tn.getEmployee()+");";
			System.out.println(query); // Qurey�� Ȯ�� 
			try { // �����ͺ��̽��� �����մϴ�. 
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
				super.stmt = conn.createStatement(); //�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
				super.stmt.executeUpdate(query); // query�� �����ŵ�ϴ�.
			stmt.close(); 
			conn.close(); 
			createTable(ID);
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection"); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
					} 
			} 	
		public void createTable(String ID){ 
			System.out.println("------------");
				String query = "CREATE TABLE "+ID+"_sale(month int primary key, sales int not null default 0, today int not null default 0, today_sale int not null default 0, expenses int not null default 0, today_expenses int not null default 0);";
				System.out.println(query); // Qurey�� Ȯ�� 
				try { // �����ͺ��̽��� �����մϴ�. 
					Class.forName(JDBC_DRIVER); 
					Class.forName(super.JDBC_DRIVER); 
					super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
					super.stmt = conn.createStatement(); //�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
					super.stmt.executeUpdate(query); // query�� �����ŵ�ϴ�.
					stmt.close(); 
					conn.close();
					createToday(ID);
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection"); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
				} 
		} 
		public void createToday(String ID){ 
			System.out.println("------------");
				String query = "CREATE TABLE "+ID+"_today(day int primary key, today_sale int not null default 0, today_expenses int not null default 0, today_total int not null);";
				System.out.println(query); // Qurey�� Ȯ�� 
				try { // �����ͺ��̽��� �����մϴ�. 
					
					Class.forName(JDBC_DRIVER); 
					Class.forName(super.JDBC_DRIVER); 
					super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
					super.stmt = conn.createStatement(); //�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
					super.stmt.executeUpdate(query); // query�� �����ŵ�ϴ�.
					stmt.close(); 
					conn.close(); 
					for(int i = 0; i<=6; i++) {
						insertToday(ID, i);
					}
					
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection"); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
				} 
		}
		private void insertToday(String id, int day) {
			// TODO Auto-generated method stub
			String query = "INSERT INTO "+id+"_today(day, today_sale, today_expenses, today_total)values("+day+",0,0,0);"; 
			System.out.println(query); // Qurey�� Ȯ�� 
			try { // �����ͺ��̽��� �����մϴ�. 
			Class.forName(super.JDBC_DRIVER); 
			super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
			super.stmt = conn.createStatement(); //�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
			super.stmt.executeUpdate(query); // query�� �����ŵ�ϴ�. 
			stmt.close(); 
			conn.close(); 
			} catch (ClassNotFoundException e) { 
				System.out.println("Class Not Found Exection"); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
				} 
		}

		public void UpdateTable(String name, String ID, String PW, String brand, float money, int employee){ 
			System.out.println("------------");
				String query = "UPDATE user SET name = '"+name+"', PW = '"+PW+"', brand = '"+brand+"', money = "+money+", employee = "+employee+" where ID = '"+ID+"';";
				System.out.println(query); // Qurey�� Ȯ�� 
				try { // �����ͺ��̽��� �����մϴ�. 
					Class.forName(JDBC_DRIVER); 
					Class.forName(super.JDBC_DRIVER); 
					super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
					super.stmt = conn.createStatement(); //�����ͺ��̽��� query���� �ѱ� Statement�� ������ݴϴ�. 
					super.stmt.executeUpdate(query); // query�� �����ŵ�ϴ�.
					stmt.close(); 
					conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection"); 
				} catch (SQLException e) { 
					System.out.println("SQL Exception : " + e.getMessage()); 
				} 
		}
	}


