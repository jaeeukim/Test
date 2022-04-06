package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.vo.Student;
import model.vo.Teacher;

public class LoginMenuManager{
	private Scanner sc = new Scanner(System.in);
	private TeacherDatabaseManager tDB = new TeacherDatabaseManager();
	private DatabaseManager sDB = new DatabaseManager();
	private SimpleDateFormat sFormat = new SimpleDateFormat("YYYY년 MM월 dd일 HH시 mm분 ss초");
	
	public void main() {
		StringBuilder menu = new StringBuilder();
		menu.append("1. 교사용 로그인\n");
		menu.append("2. 학생용 로그인\n");
		menu.append("3. 종료\n");
		menu.append(">>> ");
		
		while(true) {
			System.out.print(menu);
			
			String input = sc.nextLine();
			if(input.equals("1")) {
				teacherLogin();
			}else if(input.equals("2")) {
				studentLogin();
			}else if(input.equals("3")) {
				System.out.println("프로그램을 종료 합니다.");
				System.exit(0);
			}
		}
	}
	
	
	public void teacherLogin() {
		System.out.print("교사명 : ");
		String username = sc.nextLine();
		
		Teacher loginAccount = null;
		
		// 비밀번호 3회 입력 제한
		for(int i = 0 ; i < 3; i++) {
			System.out.print("비밀번호 : ");		
			String password = sc.nextLine();
			loginAccount = tDB.login(username, password);
			if(loginAccount != null) {
				break;
			}
		}
		
		if(loginAccount == null) {
			System.out.println("로그인에 실패했습니다. 다시 시도하세요.");
		} else {
			if(loginAccount.getLoginDate() == null) {
				System.out.println("환영합니다. 첫 로그인 입니다.");
			} else {
				Date now = new Date();
				System.out.println("최근 접속 시간은 " + loginAccount.getLoginDateFormat() + " 입니다.");
				System.out.println("현재 로그인 시간은 " + sFormat.format(now) + " 입니다.");
				loginAccount.setLoginDate(now);
			}
			loginAccount.getLoginDate();
			MenuManager tMenu = new MenuManager(loginAccount);
			tMenu.main();
		}
		
		
//		setLoginDate = dFormat.format(new Date());
//		String d2 = dFormat.format(new Date());
//		System.out.print("최근 접속 시간은 " + getLoginDate + " 입니다.\n");
//		System.out.print("현재 로그인 시간은 " + d2 + " 입니다.\n");
		

		
		
		/*
		 * 로그인 검사 후 로그인이 성공하면
		 * 최근 로그인 시간과 현재 로그인 시간을 출력 하고
		 * Teacher 객체에는 현재 로그인 시간을 loginData 에 
		 * 저장 후 MenuManager 를 실행한다.
		 * 
		 *  고명환 선생님이 접속하였습니다.
		 *  최근 접속 시간은 2022년 04월 04일 18시 30분 43초 입니다.
		 *  현재 로그인 시간은 2022년 4월 05일 09시 30분 30초 입니다.
		 *  
		 *  1. 성적조회
		 *  2. 학생 정보 추가
		 *  ...
		 *  ...
		 *  
		 *  MenuManager 의 프로그램 종료 메뉴는 로그아웃으로 바꾼다.
		 */
		
	}
	

	public void studentLogin() {
		System.out.print("학생명 : ");
		String username = sc.nextLine();
		
		Student loginAccount = null;
		
		// 비밀번호 3회 입력 제한
		for(int i = 0 ; i < 3; i++) {
			System.out.print("비밀번호 : ");		
			String password = sc.nextLine();
			loginAccount = sDB.login(username, password);
			if(loginAccount != null) {
				break;
			}
		}
		
		if(loginAccount == null) {
			System.out.println("로그인에 실패했습니다. 다시 시도하세요.");
		} else {
			Date now = new Date();
			System.out.println("현재 로그인 시간은 " + sFormat.format(now) + " 입니다.");

			StudentMenuManager sMenu = new StudentMenuManager(loginAccount);
			sMenu.main();
		}	
			
	}
	

	
}
