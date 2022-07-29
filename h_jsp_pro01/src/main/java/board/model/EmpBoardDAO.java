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

    public EmpBoardDTO selectData(int id) {
    	EmpBoardDTO result = session.selectOne("empBoardsMapper.selectData", id);
    	return result;
    }

    public boolean updateViewCnt(EmpBoardDTO data) {
    	int result = session.update("empBoardsMapper.updateViewCnt", data);

    	return result == 1 ? true : false;
    }
    
    public boolean updateLike(EmpBoardDTO data) {
    	int result = session.update("empBoardsMapper.updateLike", data);
    	
    	return result == 1 ? true : false;
    }
    
    public EmpBoardStatisDTO selectStatis(EmpBoardDTO data) {
    	EmpBoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
    	return result;
    }

    public boolean insertStatis(EmpBoardDTO data) {
    	int result = session.insert("empBoardsMapper.insertStatis", data);
    	return result == 1 ? true : false; 
    }
    
    public boolean updateStatis(EmpBoardStatisDTO data) {
    	int result = session.update("empBoardsMapper.updateStatis", data);
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