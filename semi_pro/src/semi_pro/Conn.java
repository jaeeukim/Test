package semi_pro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conn {

//	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//	private final static String BASE_URL = "jdbc:oracle:thin:@";
//	
//	private String url_address;
	private Connection conn;
	private Statement stat;
	
	
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
	
	
}
