package java_test;


public class TableName { 
	/*데이터베이스에 속성부분을 변수로 만들어 줍니다.*/ 
	String name;
	String ID;
	String PW;
	String brand;
	float money;
	int employee;
	
	/*생성자입니다.*/ 
	public TableName() {} 
	public TableName(String ID) {
		super(); 
		this.ID = ID;
	}
	public TableName(String ID, String PW) {
		super(); 
		this.ID = ID;
		this.PW = PW;
	} 
	public TableName(String name, String ID, String PW, String brand, float money, int employee) { 
		super(); 
		this.name = name; 
		this.ID = ID;
		this.PW = PW;
		this.brand = brand;
		this.money= money;
		this.employee = employee;
	} /*각 변수에 getter와 setter입니다.*/ 
	public String getName() { return name; } 
	public void setName(String name) { this.name = name; } 
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getEmployee() {
		return employee;
	}
	public void setEmployee(int employee) {
		this.employee = employee;
	}
	
	
}


