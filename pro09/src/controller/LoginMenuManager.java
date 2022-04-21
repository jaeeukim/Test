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
		menu.append("3. 비밀번호 초기화\n");
		menu.append("4. 종료\n");
		menu.append(">>> ");
		
		while(true) {
			System.out.print(menu);
		
			//sc.nextInt로 받는 방법
//			int input = 0;
//			while(true) {
//				System.out.println(menu);
//				
//				if(sc.hasNextInt()) {
//					input = sc.nextInt(); 	sc.nextLine();
//					break;
//				}
//					sc.nextLine();		
//			}				
//			switch(input) {
//			case 1: teacherLogin(); break;
//			case 2: studentLogin(); break;
//			case 3: resetPassword(); break;
//			case 4: 
//				System.out.println("프로그램을 종료합니다.");
//				System.exit(0);
//			}
			
			// sc.nextLine로 받는 방법
			String input = sc.nextLine();
			
			if(input.equals("1")) {
				teacherLogin();
			}else if(input.equals("2")) {
				studentLogin();
			}else if(input.equals("3")) {
				resetPassword();
			} else if(input.equals("4")) {
				System.out.println("프로그램을 종료 합니다.");
				System.exit(0);
			}
		}
	}
	
	
	private void resetPassword() {
		// 교사용... 학생용...
		// 1. 아이디(이름)만 입력하면 교사용에서 학생용에서 동일한 계정을 찾아서 변경하게한다.
		//    -> 전제조건 : 교사용과 학생용에 동일한 계정이 없어야한다.
		// 2. 교사용, 학생용을 먼저 선택한 후 선택한 항목에 대해 동일한 계정을 찾아서 변경하게 한다.
		System.out.print("1. 교사용 계정 \n");
		System.out.print("2. 학생용 계정 \n");
		System.out.print(">>> ");
		String type = sc.nextLine();
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		switch(type.charAt(0)) {
			case '1':
				if(tDB.isExisted(name)) {
					teacherResetPassword(name);					
				} else {
					System.out.println("해당하는 이름이 없습니다.");
				}
				break;
			case '2':
				if(sDB.isExisted(name)) {
					studentResetPassword(name);
				} else {
					System.out.println("해당하는 이름이 없습니다.");
				}
		}
	}
	
	private void teacherResetPassword(String name) {
		Teacher t = tDB.getTeacher(name);
		String password = t.resetPassWord();
		System.out.println(password + " 로 초기화되었습니다. 초기화된 패스워드로 로그인 후 다시 변경하세요.");
	}


	private void studentResetPassword(String name) {
		Student s = sDB.getStudent(name);
		String password = s.resetPassWord();
		System.out.println(password + " 로 초기화되었습니다. 초기화된 패스워드로 로그인 후 다시 변경하세요.");
		
	}


//		System.out.print("비밀번호를 초기화 할 이름을 입력하세요 : ");
//		String name = sc.nextLine();
//		
//		
//		
//		for(int i = 0; i < 6; i++) {
//			char ch = (char) ((Math.random() * 26) + 65);
//			System.out.print(ch);
//		}
	


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
			
			MenuManager sMenu = new MenuManager(loginAccount);
			sMenu.main();
			
		}	
			
	}
	

	
}
