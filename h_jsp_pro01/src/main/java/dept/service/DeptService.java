package dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	
	private DeptDAO dao;
	
	
	public List<DeptDTO> getAll() {
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchAll();
		dao.close();
		return datas;
	}
	
	//mybatis를 이용한 paging
	public List<DeptDTO> getPage(int pageNumber) {
		int start, end;
		start = (pageNumber - 1) * 10;
		end = start + 10;
		
		dao = new DeptDAO();
		List<DeptDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}
	
	
	/*
	 * paging 하는법
	public List<DeptDTO> getPage(int pageNumber) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.pfut("start", (pageNumber - 1) * 10 + 1);
		page.put("end", pageNumber * 10);
		
		dao = new DeptDAO(); 
		List<DeptDTO> datas = dao.searchPage(page);
		dao.close();
		
		return datas;
	}
	*/
	
	public List<Integer> getPageList(){ 
		dao = new DeptDAO();
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / 10 ; num++) {
			pageList.add(num + 1);
		}
		return pageList;
		
	}
	
	
	
	// 외부에서 전달하는 매개변수 타입이 다른 오버로딩이지만
	// 내부에서 처리해서 나오는 결과는 동일해서 밑에 방식으로 사용하기도 함
	/*
	public DeptDTO getId(String id) {
		int deptId = Integer.parseInt(id);
		DeptDTO data = dao.searchId(deptId);
		return data;
	}
	
	public DeptDTO getId(int id) {
		DeptDTO data = dao.searchId(id);
		return data;
	}
	
	public DeptDTO getId(DeptDTO deptDto) {
		DeptDTO data = dao.searchId(deptDto.getDeptId());
		return data;
	}
	*/
	
	public DeptDTO getId(String id) {
		return _getId(Integer.parseInt(id));
	}
	
	public DeptDTO getId(int id) {
		return _getId(id);
	}
	
	public DeptDTO getId(DeptDTO deptDto) {
		return _getId(deptDto.getDeptId());
	}
	
	private DeptDTO _getId(int id) {
		dao = new DeptDAO();
		
		DeptDTO data = dao.searchId(id);
		
		dao.close();
		return data;
	}

	public DeptDTO addDept(String deptId, String deptName, String mngId, String locId) {
		dao = new DeptDAO();
		
		DeptDTO deptDto = null;
		if(deptId.matches("\\d+") && mngId.matches("\\d+") && locId.matches("\\d+")) {
			deptDto = new DeptDTO();
			deptDto.setDeptId(Integer.parseInt(deptId));
			deptDto.setDeptName(deptName);
			deptDto.setMngId(Integer.parseInt(mngId));
			deptDto.setLocId(Integer.parseInt(locId));
			
			//DeptId는 pk
			if(dao.searchId(deptDto.getDeptId()) != null) {
				deptDto.setDeptId(-1);
				dao.rollback();
				dao.close();
				return deptDto;
			}		
			//MngId와 LocId는 fk
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				dao.rollback();
				dao.close();
				return deptDto;
			}		
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				dao.rollback();
				dao.close();
				return deptDto;
			}		
			
			
			boolean isSaved = dao.insertDept(deptDto);
			if(!isSaved) {
				dao.rollback();
				dao.close();
				return null;
			}
		}
		dao.commit();
		dao.close();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		dao = new DeptDAO();
		if(!dao.existManager(data.getMngId())) {
			dao.rollback();
			dao.close();
			return -1;
		}		
		if(!dao.existLocation(data.getLocId())) {
			dao.rollback();
			dao.close();
			return -2;
		}		
		
		boolean isSaved = dao.updateDept(data);
		if(isSaved) {
			dao.commit();
			dao.close();		
			return 1;
		}
		dao.rollback();
		dao.close();		
		return 0;
		
	}


	public int deleteDept(String id) {
		dao = new DeptDAO();
		if(dao.searchId(Integer.parseInt(id)) == null) {
			dao.rollback();
			dao.close();
			return -1; // 삭제 대상이 없음을 알림 
		}	
		
		boolean result = dao.delteDept(Integer.parseInt(id));
		if(result) {
			dao.commit();
			dao.close();
			return 1;
		}
		dao.rollback();
		dao.close();
		return 0;
	}
	
	
}
