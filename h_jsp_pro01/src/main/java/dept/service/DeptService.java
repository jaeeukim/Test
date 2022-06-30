package dept.service;

import java.util.List;

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
				dao.close();
				return deptDto;
			}		
			//MngId와 LocId는 fk
			if(!dao.existManager(deptDto.getMngId())) {
				deptDto.setMngId(-1);
				dao.close();
				return deptDto;
			}		
			if(!dao.existLocation(deptDto.getLocId())) {
				deptDto.setLocId(-1);
				dao.close();
				return deptDto;
			}		
			
			
			boolean isSaved = dao.insertDept(deptDto);
			if(!isSaved) {
				dao.close();
				return null;
			}
		}
		dao.close();
		return deptDto;
	}

	public int modifyDept(DeptDTO data) {
		dao = new DeptDAO();
		if(!dao.existManager(data.getMngId())) {
			dao.close();
			return -1;
		}		
		if(!dao.existLocation(data.getLocId())) {
			dao.close();
			return -2;
		}		
		
		boolean isSaved = dao.updateDept(data);
		dao.close();		
		if(isSaved) {
			return 1;
		}
		return 0;
		
	}
	
	
}
