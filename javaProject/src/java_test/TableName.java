package java_test;


public class TableName { 
	/*�����ͺ��̽��� �Ӽ��κ��� ������ ����� �ݴϴ�.*/ 
	String name;
	String ID;
	String PW;
	String brand;
	float money;
	int employee;
	
	/*�������Դϴ�.*/ 
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
	} /*�� ������ getter�� setter�Դϴ�.*/ 
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


