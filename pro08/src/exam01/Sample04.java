package exam01;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Sample04 {

	public static void main(String[] args) {
		/*
		 * StringTokenizer
		 * 		- String 클래스에서 제공하는 split()메서드와 유사한 기능을 제공하는 클래스
		 */
		String phone = "010-1234-5678";
		StringTokenizer st1 = new StringTokenizer(phone, "-");
		
		System.out.println(st1.countTokens());
		
		while(st1.hasMoreTokens()) {
			System.out.println(st1.nextToken());
		}
		
	
		
		
	}

}
