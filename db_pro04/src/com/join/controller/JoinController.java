package com.join.controller;

import java.sql.ResultSet;
import java.util.Scanner;

import com.conn.db.DBConn;
import com.join.dao.JoinDAO;
import com.join.view.JoinView;
import com.join.vo.JoinVO;

/*
 * 회원가입 처리를 위한 중간 제어용 객체
 */
public class JoinController {
	Scanner sc = new Scanner(System.in);
	private JoinDAO dao = new JoinDAO();
	private DBConn db;
	//중복아이디 체크 필
	public boolean join(JoinVO data) {
		switch(data.getGender()) {
			case '남':
				data.setGender('M');  break;
			case '여':
				data.setGender('F');  break;
			default:
				return false;
		}
		
		if(data.getAge() < 15) {
			return false;
		}
	
		JoinVO account = dao.get(data.getUserID());
		
		if(account != null){
			return false;
		}
		boolean result = dao.register(data);
		return result;
	}
	
	// 회원정보 수정
	// 반환타입과 매개변수는 회원정보 수정에 필요한 타입으로

	public boolean update(JoinVO data) {
		return dao.update(data);
	}
	
	
	// 회원 탈퇴
	// 반환타입과 매개변수는 회원정보 탈퇴에 필요한 타입으로		
	public boolean remove(JoinVO data) {
		return dao.remove(data);
	}

	public JoinVO login(String userid, String userpw) {
		JoinVO data = dao.get(userid);
		
		if(data.getUserPW().equals(userpw)) {
			return data;
		}
		return null;
	}

	
}
