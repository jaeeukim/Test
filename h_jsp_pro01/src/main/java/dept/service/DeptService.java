package dept.service;

import java.util.List;

import dept.model.DeptDAO;
import dept.model.DeptDTO;

public class DeptService {
	
	private DeptDAO dao;
	
	public DeptService() {
		dao = new DeptDAO();
	}
	
	public List<DeptDTO> getAll() {
		List<DeptDTO> datas = dao.searchAll();
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
		DeptDTO data = dao.searchId(id);
		return data;
	}
	
	
}
