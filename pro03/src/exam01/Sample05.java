package exam01;

import java.util.Scanner;

public class Sample05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("다음에 나열된 메뉴중 하나의 번호를 입력하세요.");
		System.out.print("1. 조회\n2. 추가\n3. 수정\n4. 삭제\n9. 종료\n");
		System.out.println(": ");
		int menu = sc.nextInt();
		
		switch(menu){
			case 1:
				System.out.println("데이터 조회 메뉴를 선택했습니다.");
				break;
			case 2:
				System.out.println("데이터 추가 메뉴를 선택했습니다.");
				break;
			case 3:
				System.out.println("데이터 수정 메뉴를 선택했습니다.");
				break;
			case 4:
				System.out.println("데이터 삭제 메뉴를 선택했습니다.");
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("잘못된 번호 입력.");
		}
		
		
	}

}
