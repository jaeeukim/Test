package model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Teacher extends Account{
	private Date loginDate; // 로그인 시간
	 
	public Teacher(String name) {
		setName(name);
		setPassword("a111"); //초기 비밀번호
	}
	
	public Teacher(String name, String password) {
		setName(name);
		setPassword(password);
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginDateFormat() {
		SimpleDateFormat sFormat = new SimpleDateFormat("YYYY년 MM월 dd일 HH시 mm분 ss초");
		return sFormat.format(loginDate);
	}

//	public boolean changePassword(String curPass, String changePass) {
//		if(curPass.equals(this.getPassword())) {
//			this.setPassword(changePass);
//			return true;
//		}
//		return false;
//	}
	
	@Override
	public String resetPassWord() {
		String prefix = "TCH_";
		String newPassword = super.resetPassWord();
		setPassword(prefix + newPassword);
		return newPassword;
	}
	
	
	

}
