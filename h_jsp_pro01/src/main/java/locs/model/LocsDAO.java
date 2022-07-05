package locs.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class LocsDAO {	
	
	SqlSession session = null;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}
	
	//전체 출력
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		return datas;
	}
	
	//id로 검색
	public LocsDTO searchId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}
	
	//추가 
	public boolean insertLocs(LocsDTO locsDto) {
		int result = session.insert("locsMapper.locsInsert", locsDto);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	//pk확인
	public boolean existCtrId(String ctrId) {
		int result = session.selectOne("locsMapper.existCtrId", ctrId);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	//업데이트(수정)
	public boolean updateLocs(LocsDTO data) {
		int result = session.update("locsMapper.locsUpdate", data);
		if(result == 1) {
			return true;
		}
		return false;
	}

	//삭제
	public boolean deleteLocs(int id) {
		int result = session.delete("locsMapper.locsDelete", id);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	
	//전체 행의 갯수
	public int totalRow() {
		int rowCount = session.selectOne("locsMapper.locsTotalRow");
		return rowCount;
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

	// mybatis 활용
	public List<LocsDTO> searchPage(int start, int end) {
		RowBounds rb = new RowBounds(start, end);
		Cursor<LocsDTO> cursor = session.selectCursor("locsMapper.locsSelectAll", null, rb);
		
		List<LocsDTO> datas = new ArrayList<LocsDTO>();
		Iterator<LocsDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		return datas;
		
		
		
	}




}


