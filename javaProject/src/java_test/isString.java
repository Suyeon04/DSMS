package java_test;

public class isString {
// ���� �Ǻ� �Լ�
	public static boolean isString1(String str) {
		boolean result;
		if(!str.matches("[0-9|a-z|A-Z|]*"))
			return false;
		return true;
	}
	public static boolean isString2(String str) {
		boolean result;
		if(!str.matches("[0-9|a-z|A-Z|��-��|��-��|��-��]*")) 
			return false;
		return true;
	}
}