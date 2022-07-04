package locs.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class LocsDAO {	
	
	SqlSession session = null;
	
	public LocsDAO() {
		session = DBConn.getSqlSession();
	}
	
	public List<LocsDTO> searchAll() {
		List<LocsDTO> datas = session.selectList("locsMapper.locsSelectAll");
		// System.out.println("LocsDAO.searchAll : " + datas);
		return datas;
	}
	
	public LocsDTO searchId(int id) {
		LocsDTO data = session.selectOne("locsMapper.locsSelectId", id);
		return data;
	}
	
	public boolean insertLocs(LocsDTO locsDto) {
		int result = session.insert("locsMapper.locsInsert", locsDto);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean existCtrId(String ctrId) {
		int result = session.selectOne("locsMapper.existCtrId", ctrId);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean updateLocs(LocsDTO data) {
		int result = session.update("locsMapper.locsUpdate", data);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean deleteLocs(int id) {
		int result = session.delete("locsMapper.locsDelete", id);
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


