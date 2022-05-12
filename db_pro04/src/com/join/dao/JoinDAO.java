package com.join.dao;

import java.io.File;
import java.sql.ResultSet;

import com.conn.db.DBConn;
import com.join.vo.JoinVO;

/*
 * 회원가입 처리를 위해 데이터베이스에 직접 엑세스하기 위한 객체로 활용
 */
public class JoinDAO {
	private DBConn db;
	
	public JoinDAO() {
		try {
			db  = new DBConn(new File(System.getProperty("user.home") + "/oracle_db.conf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원가입 처리 담당 
	public boolean register(JoinVO data) {
		String query = String.format("INSERT INTO ACCOUNTS VALUES('%s', '%s', '%s', '%c', '%d', SYSDATE)",
				  data.getUserID()
				, data.getUserPW()
				, data.getUsername()
				, data.getGender()
				, data.getAge()); 
				
		try {
			int rs = db.sendInsertQuery(query);
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	// 회원정보 수정 담당
	public boolean update(JoinVO data) {
		String query = "";
		try {
			int rs = db.sendUpdateQuery(query);
			// commit이나 rollback
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 회원정보 삭제 담당
	public boolean remove(JoinVO data) {
		String query = "";
		try {
			int rs = db.sendDeleteQuery(query);
			// commit이나 rollback
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// userid에 해당하는 회원 정보 반환
	public JoinVO get(String userid) {
		String query = String.format("SELECT * FROM accounts WHERE USERID = '%s'", userid);
		try {
			ResultSet rs = db.sendSelectQuery(query);
			if(rs.next()) {
				JoinVO data = new JoinVO();
						
				data.setUserID(rs.getString("userid"));
				data.setUserPW(rs.getString("userpw"));
				data.setUsername(rs.getString("username"));
				data.setGender(rs.getString("gender").charAt(0));
				data.setAge(rs.getInt("age"));
				data.setCreateDate(rs.getDate("createdate"));
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
