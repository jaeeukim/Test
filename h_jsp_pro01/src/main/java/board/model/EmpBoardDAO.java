package board.model;

import java.util.*;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;


public class EmpBoardDAO {

	private SqlSession session;
 
    public EmpBoardDAO() {
    	this.session = DBConn.getSqlSession();
    }

    public int getNextSeq() {
    	int result = session.selectOne("empBoardsMapper.getNextSeq");
    	return result;
    }

    public boolean insertData(EmpBoardDTO data) {
    	System.out.println("추가 전 ID : " + data.getId());
    	int result = session.insert("empBoardsMapper.insertData", data);
        System.out.println("추가 후 ID : " + data.getId());
    	return result == 1 ? true : false;
    }


	public void commit() {
		this.session.commit();
	}
	
	public void close() {
		this.session.close();
	}
	
	public void rollback() {
		this.session.rollback();
	}

}