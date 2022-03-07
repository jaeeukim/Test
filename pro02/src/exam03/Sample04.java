package exam03;

import java.util.Scanner;

public class Sample04 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫 번째 : ");
		int num1 = sc.nextInt();
		
		System.out.print("두 번째 : ");
		int num2 = sc.nextInt();
		
		int result = num1 + num2;
		System.out.printf("결과 : %d", result);
	}
}
