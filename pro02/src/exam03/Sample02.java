package exam03;

import java.util.Scanner;

public class Sample02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int iInput = sc.nextInt();
		
		System.out.println("사용자 입력값 : " + iInput);
		System.out.printf("사용자 입력값 : %d\n", iInput);
		
		double dInput = sc.nextDouble();
		
		System.out.println("사용자 입력값 : " + dInput);
		System.out.printf("사용자 입력값 : %f\n", dInput);
		
	}

}
