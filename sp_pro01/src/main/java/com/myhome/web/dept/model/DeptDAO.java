package com.myhome.web.dept.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository	// bean등록
public class DeptDAO {		
	
	@Autowired					 // DI 의존성주입, Ioc 제어역전를 해준다
	private SqlSession session;  

	
	public List<DeptDTO> searchAll() {
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectAll");
		return datas;
	}
	
	// 페이징
	public List<DeptDTO> searchPage(Map<String, Integer> page){
		List<DeptDTO> datas = session.selectList("deptMapper.deptSelectPage", page);
		return datas;
	}
	
	// mybatis를 이용해서 페이징하기
	public List<DeptDTO> searchPage(int start, int end){
		RowBounds rb = new RowBounds(start, end);
		Cursor<DeptDTO> cursor = session.selectCursor("deptMapper.deptSelectAll", null, rb);
		
		List<DeptDTO> datas = new ArrayList<DeptDTO>();
		Iterator<DeptDTO> iter = cursor.iterator();
		while(iter.hasNext()) {
			datas.add(iter.next());
		}
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
			return true;
		}
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
			return true;
		}
		return false;
	}
	
	// delete
	public boolean delteDept(int id) {
		int result = session.delete("deptMapper.deptDelete", id);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public boolean selectManager(int id) {
		int result = session.selectOne("deptMapper.existsManager", id);
		if(result >= 1) {
			return true;
		}
		return false;
	}


	public boolean selectLocation(int id) {
		int result = session.selectOne("deptMapper.existsLocation", id);
		if(result >= 1) {
			return true;
		}
		return false;
	}
	


	public int totalRow() {
		int rowCount = session.selectOne("deptMapper.deptTotalRow");
		return rowCount;
	}

	// 직접 commit하게하지말고 service에서 commit호출하도록 수정해주기
	public void commit() {
		session.commit();
	}
	public void rollback() {
		session.rollback();
	}


}
