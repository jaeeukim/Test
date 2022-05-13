package com.join.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.join.controller.JoinController;
import com.join.menu.Menu;
import com.join.vo.JoinVO;

/*
 * CLI 화면에 회원 가입, 탈퇴, 정보 수정 등과 같은 정보를 보여주기 위한 객체
 */
public class JoinView {
	Scanner sc = new Scanner(System.in);
	private JoinController jc = new JoinController();
	private Menu menu = new Menu();
	
	public void show() {
		System.out.println(menu.getMain());
		while(true) {
			System.out.print(">>> ");
			String inputNum = sc.nextLine();
			
			switch(inputNum){
			case "1" :
				this.joinMenu();
				break;
			case "2" :
				this.loginMenu();
				break;
			case "3":
				System.exit(0);
			default :
				System.out.println("번호를 잘못 입력하셨습니다");
			}			
		}
	}	
		// 회원 가입 및 로그인 요청
	private void joinMenu() {
		// 회원 가입을 위한 화면과 기능 제공
		JoinVO data = new JoinVO();

		System.out.print("아이디 : ");
		data.setUserID(sc.nextLine());
		System.out.print("패스워드 : ");
		data.setUserPW(sc.nextLine());
		System.out.print("본인이름 : ");
		data.setUsername(sc.nextLine());
		System.out.print("성별(남/여) : ");
		data.setGender(sc.next().charAt(0));
		System.out.print("나이(15세 이상) : ");
		data.setAge(sc.nextInt());
		
		
		boolean result = jc.join(data);
		
		if(result) {
			System.out.println("회원 가입을 축하합니다.");
		} else {
			System.out.println("회원 가입을 할 수 없습니다.(중복된 아이디가 있습니다)");
		}
	}
	
	public void loginMenu() {
		// 로그인을 위한 화면과 기능 제공
		System.out.print("아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("패스워드 : ");
		String userpw = sc.nextLine();		
		
		JoinVO account  = jc.login(userid, userpw);
		
		if(account != null) {
			System.out.printf("%s 님이 로그인하였습니다.\n", account.getUserID());
			afterLoginMenu(account);
			
		} else {
			System.out.println("로그인 실패하였습니다.");
		}
	}
	
	
	private void afterLoginMenu(JoinVO account) {
		while(true) {
			
			System.out.println(menu.getAfterLogin(account.getUserID()));
			System.out.print(">>> ");
			String input = sc.nextLine();
		
			switch(input) {
				case "1":
					System.out.println(account.getUserID());
					System.out.println(account.getUserPW());
					System.out.println(account.getUsername());
					System.out.println(account.getGender());
					System.out.println(account.getAge());
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
					//java.sql.Date를 java.util.Date로 변경
					java.util.Date createDate = new java.util.Date(account.getCreateDate().getTime());
					
					String sDate = dateFormat.format(account.getCreateDate().getTime());
					System.out.println(sDate);
					
					//java.util.date가 java.sql.date로 변경
					java.util.Date now = new java.util.Date();					
					java.sql.Date sqlDate = new java.sql.Date(now.getTime());
					
					//문자열을 sql.Date 타입으로 변경
					sqlDate = java.sql.Date.valueOf("2022-05-13"); //형식(-) 맞춰야함
					
					break;
				case "2":
					

					System.out.println("아무것도 입력하지 않으면 이전 값을 유지합니다.");
					System.out.println("변경 할 패스워드를 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? account.getUserPW() : input;
					account.setUserPW(input);
					
					
					System.out.println("변경할 이름을 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? account.getUsername() : input;
					account.setUsername(input);
					
					System.out.println("변경할 성별(남/여)을 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? Character.toString(account.getGender()) : input;
					account.setGender(input);
					
					System.out.println("변경할 나이를 입력하세요.");
					System.out.print(">>> ");
					input = sc.nextLine();
					input = input.isEmpty() ? Integer.toString(account.getAge()) : input;
					account.setAge(input);
					
					boolean result = jc.update(account);
					
					if(result) {
						System.out.println("수정이 완료되었습니다.");
					} else {
						System.out.println("수정에 실패했습니다.");
					}
					break;
					
					
				case "3":
					if(jc.remove(account)) {
						System.out.println("탈퇴 처리가 완료 되었습니다.");
						return;
					} else {
						System.out.println("탈퇴 처리를 수행할 수 없습니다.");
					}
					break;
					
					
					
				case "4":
					System.out.println("로그아웃 중입니다.");
					account = null;
					System.out.println("로그아웃 완료.");
					return;
				default:
					System.out.println("잘못 입력하였습니다.");
			}
		}
	}
		
	

	
}
