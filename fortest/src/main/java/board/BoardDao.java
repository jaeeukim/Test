package board;

import org.apache.ibatis.session.SqlSession;

public class BoardDao {

	SqlSession session = null;
	
	public boolean insertBoard(Board board) {
		int result = session.insert("mapper.jdbcInsert", board);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateBoard(Board board) {
		int result = session.update("mapper.jdbcUpdate", board);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	
	public void close() {
		session.close();
	}
	public void commit() {
		session.commit();
	}
	public void rollback() {
		session.rollback();
	}
}
