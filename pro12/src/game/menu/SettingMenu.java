package game.menu;

import java.util.Scanner;

import game.db.PenaltyDatabase;

public class SettingMenu {
	
	private PenaltyDatabase pDB;
	private Scanner sc;
	
	public SettingMenu() {
		this.pDB = new PenaltyDatabase();
		this.sc = new Scanner(System.in);
	}
	
	public void show() throws InterruptedException {
		String menu = "";
		
		menu += "<<<<< UP! & DOWN! >>>>>\n";
		menu += "┌──────────────────────────┐\n";
		menu += "│ 1. Penalty Add           │\n";
		menu += "│ 2. Penalty Modify        │\n";
		menu += "│ 3. Penalty Remove        │\n";
		menu += "│ 4. Back                  │\n";
		menu += "└──────────────────────────┘\n";
		menu += ": ";
		
		while(true) {
			System.out.print(menu);
			String input = sc.nextLine();

			switch(input.charAt(0)) {
				case '1':
					addMenu();
					break;
				case '2':
					modifyMenu();
					break;
				case '3':
					removeMenu();
					break;
				case '4':
					System.out.println("이전 메뉴로 돌아 갑니다.");
					Thread.sleep(500);
					return;
				default:
					System.out.println("잘못된 번호를 입력하였습니다. 다시 입력하세요.");
					System.out.println("Enter 키를 입력하면 다시 메뉴 화면이 나옵니다.");
					sc.nextLine();
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		}
		
		
	}

	private void addMenu() {
		String menu = "";
		menu += "추가할 벌칙을 작성하세요. 작성을 중단하려면 exit 를 입력하세요.\n";
		menu += ": ";
		
		while(true) {
			System.out.print(menu);
			String input = sc.nextLine();
			
			if(input.equals("exit")) {
				System.out.println("추가 작업을 종료하고 이전 메뉴로 돌아갑니다.");
				return;
			}
			
			pDB.add(input);
			System.out.println("입력한 벌칙 저장이 완료되었습니다.");
		}		
	}
	
	private void modifyMenu() {
		int numberMax = penaltyListUp();
		
		System.out.println("위 목록 중에서 수정할 벌칙을 선택하세요.");
		System.out.print(": ");
		
		int number;
		while(true) {
			if(sc.hasNextInt()) {
				number = sc.nextInt(); sc.nextLine();
				if(number > 0 && number < numberMax + 1) {
					break;					
				}
				System.out.printf("입력 번호는 1 ~ %d 까지 입니다.\n", numberMax);
			} else {
				System.out.println("다시 입력하세요.");
				sc.nextLine();
			}
			System.out.print(": ");
		}
		
		System.out.println("벌칙을 입력하세요.");
		System.out.print(": ");
		String penalty = sc.nextLine();
		
		pDB.modify(number - 1, penalty);
		System.out.println("입력한 벌칙으로 수정이 완료되었습니다.");
	}

	private void removeMenu() {
		int numberMax = penaltyListUp();	
		
		System.out.println("위 목록 중에서 삭제할 벌칙을 선택하세요.");
		System.out.print(": ");
		
		int number;
		while(true) {
			if(sc.hasNextInt()) {
				number = sc.nextInt(); sc.nextLine();
				if(number > 0 && number < numberMax + 1) {
					break;					
				}
				System.out.printf("입력 번호는 1 ~ %d 까지 입니다.\n", numberMax);
			} else {
				System.out.println("다시 입력하세요.");
				sc.nextLine();
			}
			System.out.print(": ");
		}
		
		pDB.remove(number - 1);
		System.out.println("선택한 벌칙 삭제가 완료되었습니다.");
	}
	
	private int penaltyListUp() {
		String[] penaltys = pDB.getList();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < penaltys.length; i++) {
			sb.append(String.format("%d. %s\n", i + 1, penaltys[i]));
		}
		System.out.print(sb.toString());	
		return penaltys.length;
	}


}
