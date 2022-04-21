package model.vo;

import java.util.Random;

// 학생 정보를 가지는 클래스
public class Student extends Account{
	/*
	 * 이름(name)과 성적배열(Grade)를 정의
	 * getter/setter도 작성
	 */
	
	Random rand = new Random();
	private Grade[] grades;
	
	public Student(String name) {
		setName(name);
		setPassword("a111");
	}

	public Grade[] getGrades() {
		return grades;
	}

	public void setGrades(Grade[] grades) {
		this.grades = grades;
	}



	@Override
	public String resetPassWord() {
		String prefix = "STD_";
		String newPassword = super.resetPassWord();
		setPassword(prefix + newPassword);
		return newPassword;
	}
	
	
}
