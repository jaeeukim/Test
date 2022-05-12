package com.conn.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBConn {

	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String BASE_URL = "jdbc:oracle:thin:@";
	
	private String url_address;
	private Connection conn;
	private Statement stat;
	
	public DBConn(File config) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(config));
		while(br.ready()) {
			String[] keyValue = br.readLine().split("=");
			map.put(keyValue[0].strip(), keyValue[1].strip());  //strip 공백제거
		}
		url_address = String.format("%s:%s/%s", map.get("host"), map.get("port"), map.get("service"));
		this.initConnect(map.get("username"), map.get("password"));	
			
	}
	
	public DBConn(String domain, String port, String serviceName, String username, String password) throws Exception {
		url_address = String.format("%s:%s/%s", domain, port, serviceName);
		this.initConnect(username, password);
	}
	
	private void initConnect(String username, String password) throws Exception{
		// 1. Driver 등록
		Class.forName(DRIVER_NAME);
		
		// 2. DBMS 연결
		conn = DriverManager.getConnection(BASE_URL + url_address, username, password);
		conn.setAutoCommit(false);
		// "localhost:1521/XEPDB1"
		
		// 3. Statement 생성
		stat = conn.createStatement();
	}
	
	public ResultSet sendSelectQuery(String sql) throws Exception{
		ResultSet rs = this.stat.executeQuery(sql);
		return rs;
	}
	
	public int sendUpdateQuery(String sql) throws Exception{
		int rs = this.stat.executeUpdate(sql);
		return rs;
	}

	public int sendInsertQuery(String sql) throws Exception{
		int rs = this.stat.executeUpdate(sql);
		return rs;
	}
	
	public int sendDeleteQuery(String sql) throws Exception{
		int rs = this.stat.executeUpdate(sql);
		return rs;
	}
	
	public void commit() throws Exception{
		this.conn.commit();
	}
	
	public void rollback() throws Exception{
		this.conn.rollback();
	}
	
	public void close() throws Exception {
		// 5. 연결 해제
		this.stat.close();
		this.conn.close();
	}
	

	// 테스트용이라 지워버리기
//	public static void main(String[] args) throws Exception {
//		// 로컬
//		DBConn myDB = new DBConn("localhost", "1521", "XEPDB1", "puser1", "puser1");		
//		// 오라클클라우드 
//		// DBConn myDB = new DBConn("db20220411241_medium", "C:\\User\\user1\\eclipse\\oracle\\Wallet_DB202204211241", "puser1", "Database1234");		
//	
//		
//		 int rowCount = myDB.sendInsertQuery("INSERT INTO DEPARTMENTS VALUES(280, 'Dept Test', NULL, 1700)");
//		// int rowCount = myDB.sendUpdateQuery("UPDATE DEPARTMENTS SET DEPARTMENT_NAME = 'Tester' WHERE DEPARTMENT_ID = 280");
//		// int rowCount = myDB.sendDeleteQuery("DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = 280");
//		System.out.println(rowCount + " 개 행이 반영되었습니다.");
//		
//		ResultSet rs = myDB.sendSelectQuery("SELECT * FROM DEPARTMENTS");
//		while(rs.next()) {
//			System.out.print(rs.getInt("DEPARTMENT_ID") + "\t");
//			System.out.print(rs.getString("DEPARTMENT_NAME") + "\t");
//			System.out.print(rs.getInt("MANAGER_ID") + "\t");
//			System.out.print(rs.getInt("LOCATION_ID") + "\t");
//			System.out.println("");
//		}
//		
//		
//		myDB.commit(); // myDB.rollback();
//		
//		rs.close();
//		myDB.close();
//	}

}
