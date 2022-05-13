package com.join.dao;

import java.io.File;
import java.sql.PreparedStatement;
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
		//statement 사용법
//		String query = String.format("INSERT INTO ACCOUNTS VALUES('%s', '%s', '%s', '%c', '%d', SYSDATE)",
//				  data.getUserID()
//				, data.getUserPW()
//				, data.getUsername()
//				, data.getGender()
//				, data.getAge()); 
//			
		// PreparedStatement 사용법
		String query = "INSERT INTO accounts VALUES(?, ?, ?, ?, ?, SYSDATE";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserID());
			pstat.setString(2, data.getUserPW());
			pstat.setString(3, data.getUsername());
			pstat.setString(4, Character.toString(data.getGender()));
			pstat.setInt(5, data.getAge());
			
			
			int rs = db.sendInsertQuery();
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
	//statement
//	public boolean update(JoinVO data) {
//		String query = "UPDATE accounts"
//				+ "        SET USERPW = '" + data.getUserpw() + "'"
//				+ "          , USERNAME = '" + data.getUsername() + "'"
//				+ "          , GENDER = '" + data.getGender() + "'"
//				+ "          , AGE = " + data.getAge()
//				+ "      WHERE USERID = '" + data.getUserid() + "'";
//		try {
//			int rs = db.sendUpdateQuery();
//			// commit이나 rollback
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	//pstat
	public boolean update(JoinVO data) {
		String query = "UPDATE accounts"
				+ "        SET USERPW = ?"
				+ "          , USERNAME = ?"
				+ "          , GENDER = ?"
				+ "          , AGE = ?" 
				+ "      WHERE USERID = ?";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserID());
			pstat.setString(2, data.getUserPW());
			pstat.setString(3, data.getUsername());
			pstat.setString(4, Character.toString(data.getGender()));
			pstat.setInt(5, data.getAge());
			
			int rs = db.sendUpdateQuery();
			// commit이나 rollback
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	
	// 회원정보 삭제 담당
	//statement
//	public boolean remove(JoinVO data) {
//		String query = "DELETE FROM accounts WHERE USERID = '" + data.getUserid() + "'";
//		try {
//			int rs = db.sendDeleteQuery(query);
//			if(rs == 1) {
//				db.commit();
//				return true;
//			}
//			db.rollback();
//			// commit이나 rollback
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	
	// pstat
	public boolean remove(JoinVO data) {
		String query = "DELETE FROM accounts WHERE USERID = ? ";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, data.getUserID());
			
			int rs = db.sendDeleteQuery();
			// commit이나 rollback
			if(rs == 1) {
				db.commit();
				return true;
			}
			db.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	// userid에 해당하는 회원 정보 반환
	//statement
//	public JoinVO get(String userid) {
//		String query = String.format("SELECT * FROM accounts WHERE USERID = '%s'", userid);
//		try {
//			ResultSet rs = db.sendSelectQuery();
//			if(rs.next()) {
//				JoinVO data = new JoinVO();
//						
//				data.setUserID(rs.getString("userid"));
//				data.setUserPW(rs.getString("userpw"));
//				data.setUsername(rs.getString("username"));
//				data.setGender(rs.getString("gender").charAt(0));
//				data.setAge(rs.getInt("age"));
//				data.setCreateDate(rs.getDate("createdate"));
//				return data;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	//pstat
	public JoinVO get(String userid) {
		String query = "SELECT * FROM accounts WHERE USERID = ?";
		try {
			PreparedStatement pstat = db.getPstat(query);
			pstat.setString(1, userid);
						
			ResultSet rs = db.sendSelectQuery();
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
