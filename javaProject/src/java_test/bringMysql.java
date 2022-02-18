package java_test;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class bringMysql {
	static String id;
	static mysql_1 m;
	static mysql_2 m2;
	static mysql_3 m3;
	static mysql_4 m4;
	static LocalDate now;
	
	
	static String name;
	static String[] item;
	static String brand;
	static String pw;
	static float money; 
	static int employee;
	static float percent;
	
	
	static int year;
	static int month;
	static int day;
	static int day1;
	static int yearPlus;
	
	
	static int[] bringItem2; 
	static int money1; // 매출액
	static int money2; // 인건비 등 지출비
	static int a[];
	static int b[];
	static int[] c;
	static int today_sales[];
	static int today_expenseses[];
	static int totals[];
	static int[] bringItem3; 
	static int today_sale;
	static int today_expenses;
	
	public bringMysql(String id) {
		this.id = id;
		m = new mysql_1();
		m2 = new mysql_2();
		m3 = new mysql_3();
		m4 = new mysql_4();
	    now = LocalDate.now();
		
		
		name = m.bringName(id);
		item = m.bringItem(id);
		brand = item[1];
		pw = item[0];
		money = m.bringMoney(id); 
		employee = m.bringEmp(id);
		
		
		year = now.getYear();
		month = now.getMonthValue();
		day = now.getDayOfMonth();
		day1 = now.getDayOfWeek().getValue()-1;
	    yearPlus = year*100+month;
		
		
		bringItem2 = m3.bringItem(id,yearPlus); 
		money1 = bringItem2[0]; // 매출액
		money2 = bringItem2[1]; // 인건비 등 지출비
		bringItem3 = m3.bringItem2(id, yearPlus,day);
		today_sale = bringItem3[0];
		today_expenses =  bringItem3[1];
		
		if(today_sale == 0) {
			m4.UpdateToday(id, day1, 0, 0, 0);
		} 
		a = new int[12];
		for(int i=1; i<=12; i++) {
			a[i-1] = m3.bringGraph(id, year, i)/1000000;
		}
		
		b = new int[12];
		for(int i=1; i<=12; i++) {
			bringItem3 = m3.bringItem(id,(year*100)+i);
			b[i-1] =  (((int)(bringItem3[0]*money/100) - bringItem3[1] - employee)/100000);
		}
		
		today_sales = new int[7];
		today_expenseses = new int[7];
		totals = new int[7];
		c= new int[3];
		for(int i = 0; i<7; i++) {
			c = m4.showToday(id, i);
			today_sales[i] = c[0]/50000;
			today_expenseses[i]= c[1];
			totals[i]=(c[2]-(employee/30))/5000;
		}
		
	}
}
