package semi_pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class DeConn {
	
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String URL =  "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private final static String BASE_URL = "jdbc:oracle:thin:@";
	
	private static final String USER_NAME = "FOR_SEMI";
	private static final String PASSWORD = "FOR_SEMI";
	
	private String url_address;
	//private Statement stat;
	private Connection conn2;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public DeConn(String domain, String port, String serviceName, String username, String password) throws Exception {
		url_address = String.format("%s:%s/%s", domain, port, serviceName);
		this.initConnect(username, password);
	}
	
	private void initConnect(String username, String password) throws Exception {
		// Driver 등록
		Class.forName(DRIVER_NAME);
		
		// DBMS 연결
		conn2 = DriverManager.getConnection(BASE_URL + url_address, username, password);
		conn2.setAutoCommit(false);
		
		//  Statement 생성
	//	 stat = conn2.createStatement();
	}
	
	public void close() throws Exception {
		// 5. 연결 해제
	//	this.stat.close();
		this.pstat.close();
		this.conn2.close();
	}
	
	
	public void commit() throws Exception{
		this.conn2.commit();
	}
	
	public void rollback() throws Exception{
		this.conn2.rollback();
	}
	
	
	
	public PreparedStatement getPstat(String sql) throws Exception {
		pstat = conn2.prepareStatement(sql);
		return pstat;
	}
	
	
	public ResultSet sendSelectQuery() throws Exception{
		rs = this.pstat.executeQuery();
		return rs;
	}
	
	public int sendInsertQuery() throws Exception{
		int rs = this.pstat.executeUpdate();
		return rs;
	}
	
	public int sendDeleteQuery() throws Exception{
		int rs = this.pstat.executeUpdate();
		return rs;
	}
	
	public int sendUpdateQuery() throws Exception{
		int rs = this.pstat.executeUpdate();
		return rs;
	}
	
	
	
		
	
	
	
}
