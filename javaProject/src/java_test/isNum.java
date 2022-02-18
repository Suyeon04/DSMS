package java_test;

public class isNum {
	// 숫자 판별 함수
	public static boolean isNum(String str) {
	char tmp;
	for(int i=0; i<str.length(); i++) {
	tmp = str.charAt(i);
	if(!('0' <= tmp && tmp <= '9' || tmp == '.')) {
	return false;
	}
	}
	return true;
	}
	}
