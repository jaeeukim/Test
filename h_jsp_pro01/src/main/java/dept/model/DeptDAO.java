package dept.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import conn.db.DBConn;

public class DeptDAO {
	
	SqlSession session = null;
	
	public DeptDAO() {
		session = DBConn.getSqlSession();
	}
	
	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		System.out.println("DeptDAO.searchAll : " + datas);
		return datas;
	}

}
