package model.vo;

import java.util.Random;

import exception.PasswordUnvalidException;

public abstract class Account {
	private String name;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		
		if(!(password.matches("[0-9a-zA-Z]{4,12}")
				&& !password.matches("[0-9]{4,12}")
				&& !password.matches("[a-zA-Z]{4,12}"))) {
			throw new PasswordUnvalidException("패스워드에는 숫자/영문자 조합으로 만들어야 합니다.");
		}
		this.password = password;
	}
	
	// 패스워드 변경
	public boolean changePassword(String curPass, String changePass) {
		if(curPass.equals(this.getPassword())) {
			this.setPassword(changePass);
			return true;
		}
		return false;
	}
	// 패스워드를 초기화 (영문자 6자를 임의로 생성하여 초기화한다.)
	// 학생용 계정은 STD_ 접두사가 붙어서 생성되게 한다.
	// 선생님용 계정은 TCH_ 접두사가 붙어서 생성되게 한다.
	
	
	public String resetPassWord() {
		String newPassword = "";
		
		for(int i = 0; i < 6; i++) {
			Random r = new Random();
			if(r.nextBoolean()) {
				// 참으로 나오면 대문자
				newPassword += (char)(r.nextInt(26) + 65); 
			} else {
				// 거짓으로 나오면 소문자
				newPassword += (char)(r.nextInt(26) + 97); 
			}
		}
		setPassword(newPassword);
		return newPassword;
	}
	
	
	
	
	
	
	
	
	
	
}
