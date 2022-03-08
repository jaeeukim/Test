package exam01;

import java.util.Scanner;

public class Sample02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1. 1 ~ 99까지의 정수 값을 입력받기
		/*System.out.print("정수값을 입력하세요 : ");
		int num = sc.nextInt();
		
		if(num < 1 || num >99) {
			System.out.println("1 ~ 99 사이의 값을 입력하시오.");
		} else {
			System.out.println("정수값이 정상적으로 입력되었습니다.");
		}
		
		System.out.println("========================");
		*/
		
		
		// 2. 0 ~ 100 사이의 정수값을 입력받기
		
		System.out.print("0 ~ 100 사이의 정수를 입력하시오 : ");
		int num2 = sc.nextInt();
		
		if(num2 <=39) {
			System.out.println("과락입니다.");
		} else if(num2 <=59) {
			System.out.println("E 등급 입니다.");
		} else if(num2 <=69) {
			System.out.println("D 등급 입니다.");
		} else if(num2 <=79) {
			System.out.println("C 등급 입니다.");
		} else if(num2 <=89) {
			System.out.println("B 등급 입니다.");
		} else if(num2 <=100) {
			System.out.println("A 등급 입니다.");
		}
		
		
		// 2번 다른 방식의 풀이
		
//		String result;
//		result = ""; //없으면 초기화가 안되는경우가있다고 오류가남 
//		if(num2 <=39) {
//			result = "과락입니다.";
//		} else if(num2 <=59) {
//			result = "E 등급 입니다.";
//		} else if(num2 <=69) {
//			result = "D 등급 입니다.";
//		} else if(num2 <=79) {
//			result = "C 등급 입니다.";
//		} else if(num2 <=89) {
//			result = "B 등급 입니다.";
//		} else if(num2 <=100) {
//			result = "A 등급 입니다.";
//		}
//		
//		System.out.println(result);
//		
		
		
		
	}

}
