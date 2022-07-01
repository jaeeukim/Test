package dept.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class DeptDAO {
	
	SqlSession session = null;
	
	public DeptDAO() {
		session = DBConn.getSqlSession();
	}
	
	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
	
	// 페이징
	public List<DeptDTO> searchPage(Map<String, Integer> page){
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	
	
	
	
	
	// 해당 id값 찾기
	public DeptDTO searchId(int id) {
		DeptDTO data = session.selectOne("deptMapper.deptSelectId", id);
		return data;
	}

	// insert
	public boolean insertDept(DeptDTO deptDto) {
		int result = session.insert("deptMapper.deptInsert", deptDto); //result값만큼 insert됨
		if(result == 1) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}

	// 존재유무 확인
	public boolean existManager(int id) {
		int result = session.selectOne("deptMapper.existManager", id);
		if(result == 1 ) {
			return true;
		} 
		return false;
	}


	public boolean existLocation(int id) {
		int result = session.selectOne("deptMapper.existLocation", id);
		if(result == 1 ) {
			return true;
		} 
		return false;
	}

	// update
	public boolean updateDept(DeptDTO deptDto) {
		int result = session.update("deptMapper.deptUpdate", deptDto);
		if(result == 1) {
			session.commit();
			return true;
		}
		session.rollback();
		return false;
	}
	
	// delete
	public boolean delteDept(int id) {
		int result = session.delete("deptMapper.deptDelete", id);
		if(result == 1) {
			session.commit();
			return true;
		}
		return false;
	}

	
	public void close() {
		session.close();
	}


	public int totalRow() {
		int rowCount = session.selectOne("deptMapper.deptTotalRow");
		return rowCount;
	}


	

}
