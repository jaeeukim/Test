package emps.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import conn.db.DBConn;
import dept.model.DeptDTO;

public class EmpDAO extends AbstractDAO{

	private String mapper = "empMapper.%s";
	
	public EmpDTO selectId(int id) {
		String mapId = String.format(mapper, "selectId");
		EmpDTO data = session.selectOne(mapId, id);
		return data;
	}

	
	public List<EmpDTO> selectAll() {
		String mapId = String.format(mapper, "selectAll");
		List<EmpDTO> datas = session.selectList(mapId);
		return datas;
	}

	public List<EmpDTO> selectPage(int offset, int count) {
		String mapId = String.format(mapper, "selectAll");
		RowBounds rb = new RowBounds(offset, count); // offset위치붵 count갯수만큼

		Cursor<EmpDTO> cursor = session.selectCursor(mapId, null, rb);
		
		List<EmpDTO> datas = new ArrayList<EmpDTO>();
		Iterator<EmpDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
		return datas;
	}

	public int totalRow() {
		String mapId = String.format(mapper, "totalRow");
		int rowCount = session.selectOne(mapId);
		return rowCount;
	}

	public EmpDetailDTO selectDetail(int empId) {
		String mapId = String.format(mapper, "selectDetail");
		EmpDetailDTO data = session.selectOne(mapId, empId);
		return data;
	}

	public boolean updateEmployee(EmpDTO updateEmpData) {
		String mapId = String.format(mapper, "updateEmployee");
		int result = session.update(mapId, updateEmpData);
		return result == 1 ? true : false;
	}

	public boolean updateEmployeeDetail(EmpDetailDTO updateEmpDetailData) {
		String mapId = String.format(mapper, "updateEmployeeDetail");
		int result = session.update(mapId, updateEmpDetailData);
		return result == 1 ? true : false;
	}

//	public EmpDTO searchId(int deptId) {
//		String mapId = String.format(mapper, "empSelectId");
//		EmpDTO datas = session.selectOne(mapId, deptId);
//		return datas;
//	}

//	public boolean insertDept(EmpDTO empDto) {
//		String mapId = String.format(mapper, "empInsert");
//		int result = session.insert(mapId, empDto);
//		return result == 1 ? true : false;
//	}


	public boolean insertEmployee(EmpDTO empData) {
		String mapId = String.format(mapper, "insertEmployee");
		int result = session.insert(mapId, empData);
		return result == 1 ? true : false;
	}




	

	
}
