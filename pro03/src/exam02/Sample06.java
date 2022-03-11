package exam02;

import java.util.Scanner;

public class Sample06 {

	public static void main(String[] args) {
		
		// 1 ~ 10 까지의 누적합 구하기
		int result = 0;
		
		for(int i = 1; i <= 10; i++) {
			result += i;
		}
		System.out.printf("1 ~ 10 누적합 = %d", result);
		
		// 사용자가 입력한 정수값에 해당하는 구구단 출력
		System.out.print("1 ~ 9까지 숫자를 입력하시오 : ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		for(int i = 1; i < 10; i++) {
			System.out.printf(num + " X " + i + " = %d\n", num * i);
		}
		
		// 구구단을 출력할 때 한 줄에 3개의 결과 출력
		System.out.print("1 ~ 9까지 숫자를 입력하시오 : ");
		int n = sc.nextInt();
		
		int num2 = 0;
		
		for(int i = 1; i < 10; i++) {
			System.out.printf("%d  X  %d = %d\t",n ,i,  n * i);
			num2++;
			if(num2 == 3) {
				System.out.println("");
				num2 = 0;
			}
		}
		
		
		
		
		
		
		
		
	}
}
