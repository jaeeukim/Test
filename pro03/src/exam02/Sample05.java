package exam02;

import java.util.Scanner;

public class Sample05 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int menu;
		boolean existed = false;
		
		System.out.print("다음의 메뉴 번호 중 하나를 입력하시오.");
		for(int i = 0; i < 3; i++) {
			System.out.println("1. 조회");
			System.out.println("2. 추가");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("9. 종료");
			System.out.println(": ");
			menu = sc.nextInt();
			
			switch(menu) {
				case 1:
					System.out.println("조회 메뉴를 선택했습니다.");
					existed = true;
					break;
				case 2:
					System.out.println("추가 메뉴를 선택했습니다.");
					existed = true;
					break;
				case 3:
					System.out.println("수정 메뉴를 선택했습니다.");
					existed = true;
					break;
				case 4:
					System.out.println("삭제 메뉴를 선택했습니다.");
					existed = true;
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					existed = true;
					break;
				default:
					System.out.println("잘못된 메뉴 번호 입니다. 다시 입력.");
					}
			if(existed) {
				break;
			}
		}
	}

}
