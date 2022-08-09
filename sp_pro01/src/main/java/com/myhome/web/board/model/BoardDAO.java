package com.myhome.web.board.model;

import java.util.*;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class BoardDAO {

	@Autowired
	private SqlSession session;

	public List<BoardDTO> selectAll() {
		List<BoardDTO> result = session.selectList("boardMapper.selectAll");
		return result;
	}

    public int getNextSeq() {
    	int result = session.selectOne("empBoardsMapper.getNextSeq");
    	return result;
    }

    public boolean insertData(BoardDTO data) {
    	System.out.println("추가 전 ID : " + data.getId());
    	int result = session.insert("empBoardsMapper.insertData", data);
        System.out.println("추가 후 ID : " + data.getId());
    	return result == 1 ? true : false;
    }

    public BoardDTO selectData(int id) {
    	BoardDTO result = session.selectOne("empBoardsMapper.selectData", id);
    	return result;
    }

    public boolean updateViewCnt(BoardDTO data) {
    	int result = session.update("empBoardsMapper.updateViewCnt", data);
    	return result == 1 ? true : false;
    }
    
    public boolean updateLike(BoardDTO data) {
    	int result = session.update("empBoardsMapper.updateLike", data);
    	return result == 1 ? true : false;
    }
    
    public BoardStatisDTO selectStatis(BoardStatisDTO data) {
    	BoardStatisDTO result = session.selectOne("empBoardsMapper.selectStatis", data);
    	return result;
    }

    public boolean insertStatis(BoardStatisDTO data) {
    	int result = session.insert("empBoardsMapper.insertStatis", data);
    	return result == 1 ? true : false; 
    }
    
    public boolean updateStatis(BoardStatisDTO data) {
    	int result = session.update("empBoardsMapper.updateStatis", data);
    	return result == 1 ? true : false;     	
    }

    public boolean updateStatis(BoardStatisDTO data, String type) {
    	if(type.equals("like")) {
    		int result = session.update("empBoardsMapper.updateLikeStatis", data);    		
    		return result == 1 ? true : false;     	
    	} else {
    		return updateStatis(data);     	    		
    	}
    }
    /*
    public void selectPage(Paging paging) {
    	RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
    	
    	Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", null, rb);
    	paging.setPageDatas(cursor.iterator());
    }
    
	public void selectPage(Paging paging, String search) {
		RowBounds rb = new RowBounds(paging.getOffset(), paging.getLimit());
		Cursor<Object> cursor = session.selectCursor("empBoardsMapper.selectPage", search, rb);
		paging.setPageDatas(cursor.iterator());
	}
	
    public int totalRow() {
    	int rowCount = session.selectOne("empBoardsMapper.boardTotalRow");
    	return rowCount;
    }
    
    public int totalRow(String search) {
    	int rowCount = session.selectOne("empBoardsMapper.boardTotalRow", search);
    	return rowCount;
    }
    
	public List<EmpBoardDTO> searchAll() {
		List<EmpBoardDTO> datas = session.selectList("empBoardsMapper.boardSelectAll");
		return datas;
	}
	public EmpBoardDTO searchId(int id) {
		EmpBoardDTO data = session.selectOne("empBoardsMapper.boardSelectId", id);
		return data;
	}
	
   
	public boolean updateData(BoardDTO data) {
		int result = session.update("empBoardsMapper.updateData", data);
		return result == 1 ? true : false;
	}
	
	public boolean deleteStatisData(BoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteStatisData", data.getId());
		return result >= 0 ? true : false;		
	}
	
	public boolean deleteData(BoardDTO data) {
		int result = session.delete("empBoardsMapper.deleteData", data.getId());
		return result == 1 ? true : false;
	}  
	*/
	
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