package exam02;

import java.util.Random;
import java.util.Scanner;

public class Sample12 {

	public static void main(String[] args) {
		//배열을 적용한다.
		// 		- 컴퓨터가 생성한 정수 값을 가위, 바위, 보로 치환하기 위한 배열
		// 		- 전적 기록을 배열을 사용하여 관리할 수 있도록 한다.
		
		
		Scanner sc = new Scanner(System.in); 
		Random random = new Random();
		
		System.out.print("당신의 이름을 입력해주세요 : ");
		String s = sc.nextLine();
		String A, B = "";
		int c1 = 0, c2 = 0, c3 = 0, tot = 0;
		// 강사님 int result;만들고 이기면1, 지면 -1, 비기면 0으로 만듦
		
		while(true) {
			System.out.print("가위바위보 : ");
			A = sc.nextLine();
		
			if(A.equals("exit")) {
				break;
			}
			
			if(random.nextInt(3) == 0) {
				B = "가위";
			} else if(random.nextInt(3) == 1) {
				B = "바위";
			} else if(random.nextInt(3) == 2) {
				B = "보";
			} 
			System.out.println("컴퓨터 : " + B);
			System.out.println(s + " : " + A);
			
			if(A.equals(B)) {
				System.out.println("비겼습니다.");
				c1++;
				tot++;
			}else if(A.equals("가위") && B.equals("바위") || A.equals("바위") && B.equals("보") ||A.equals("보") && B.equals("가위") ) {
				System.out.println("졌습니다ㅜㅜ");
				c2++;
				tot++;
			}else if(A.equals("보") && B.equals("바위") || A.equals("가위") && B.equals("보") ||A.equals("바위") && B.equals("가위") ) {
				System.out.println("이겼습니다!!!");
				c3++;
				tot++;
			}else {
				System.out.println("잘못 입력하셨습니다.");
				c1++;
				tot++;
			}
			System.out.println("");
			
		
			
		}
		System.out.printf("%d전 %d승 %d무 %d패", tot, c3, c1, c2);
	}

}
