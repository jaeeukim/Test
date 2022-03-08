package exam4;

import java.util.Scanner;

public class Sample04 {

	public static void main(String[] args) {
		// 섭씨 화씨 변환 (섭씨 * (9/5)) + 32
		
		Scanner sc = new Scanner(System.in);
		int num1;
		double num2;
		
		System.out.print("℃ -> ℉로 변환합니다.\n온도 입력(℃) : ");
		num1 = sc.nextInt();
		
		
		num2 = (num1* 9.0/5.0) + 32;
		System.out.printf("℉ : %.2f", num2);
		
		
	}

}
