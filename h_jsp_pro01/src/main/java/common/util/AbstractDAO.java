package common.util;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;
import oracle.net.aso.ab;

public abstract class AbstractDAO {
	protected SqlSession session;
	
	public AbstractDAO() {
		session = DBConn.getSqlSession();
	}
	
	
	public void commit() {
		session.commit();
	}
	public void close() {
		session.close();
	}
	public void rollback() {
		session.rollback();
	}
}
