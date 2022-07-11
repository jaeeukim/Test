package emps.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import common.util.AbstractDAO;
import conn.db.DBConn;

public class EmpDAO extends AbstractDAO{

	private String mapper = "empMapper.%s";
	
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
	

	
}
