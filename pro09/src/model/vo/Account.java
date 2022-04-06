package model.vo;

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
		this.password = password;
	}
	
	// 패스워드 변경
	public abstract boolean changePassword(String curPass, String changePass);
	
	// 패스워드를 초기화 (영문자 6자를 임의로 생성하여 초기화한다.)
	// 학생용 계정은 STD_ 접두사가 붙어서 생성되게 한다.
	// 선생님용 계정은 TCH_ 접두사가 붙어서 생성되게 한다.
	public abstract String resetPassWord();
	
}
