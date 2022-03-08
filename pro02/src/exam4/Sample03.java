package exam4;

import java.util.Scanner;

public class Sample03 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int num1, num2, r1, r2, r3, r5;
		double r4;
		
		System.out.print("첫 번째 값 입력 : ");
		num1 = sc.nextInt();
		System.out.print("두 번째 값 입력 : ");
		num2 = sc.nextInt();
		
		r1 = num1 + num2;
		r2 = num1 -num2;
		r3= num1 * num2;
		r4 = (double)num1 / num2;
		r5 = num1 % num2;
		
		System.out.println("더하기 계산 결과 : " + r1);
		System.out.println("빼기 계산 결과 : " + r2);
		System.out.println("곱하기 계산 결과 : " + r3);
		System.out.printf("나누기 계산 결과 : %.4f\n", r4);
		System.out.println("나머지 계산 결과 : " + r5);
		
		
		
	}

}
