package emps.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import dept.model.DeptDAO;
import dept.model.DeptDTO;
import emps.model.EmpDAO;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;


public class EmpService {
	
	public boolean add(EmpDTO empData, EmpDetailDTO empDetailData) {
		EmpDAO dao = new EmpDAO();
		boolean res1 = dao.insertEmployee(empData);
		boolean res2 = dao.updateEmployeeDetail(empDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}
	
	
	
	public EmpDTO getId(String empId) {
		EmpDAO dao = new EmpDAO();
		EmpDTO data = dao.selectId(Integer.parseInt(empId));
		dao.close();
		return data;
	}
	
	
	public List<EmpDTO> getEmpAll() {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectAll();
		dao.close();
		return datas;
	}

	public List<EmpDTO> getEmpPage(int page, int count) {
		EmpDAO dao = new EmpDAO();
		List<EmpDTO> datas = dao.selectPage((page - 1) * count, count);
		dao.close();
		return datas;
	}

	public List<Integer> getPageList(int pageCount) {
		EmpDAO dao = new EmpDAO();
		List<Integer> pageList = new ArrayList<Integer>();
		
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total -1)/pageCount; num++) {
			pageList.add(num + 1);
		}
		
		dao.close();
		return pageList;
	}

	public EmpDetailDTO getDetail(int empId) {
		EmpDAO dao = new EmpDAO();
		EmpDetailDTO data = dao.selectDetail(empId);
		dao.close();
		return data;
	}

	public boolean setEmployee(EmpDTO updateEmpData, EmpDetailDTO updateEmpDetailData) {
		EmpDAO dao = new EmpDAO();
		
		String email = updateEmpData.getEmail();
		if(email.contains("@emp.com")) {
			email = email.replace("@emp.com", "");
			updateEmpData.setEmail(email);
		}
		
		
		boolean res1 = dao.updateEmployee(updateEmpData);
		boolean res2 = dao.updateEmployeeDetail(updateEmpDetailData);
		
		if(res1 && res2) {
			dao.commit();
			dao.close();
			return true;
		} 
		dao.rollback();
		dao.close();
		return false;
	}
	
	
//	public EmpDTO addDept(String deptId, String empName, String email, String jobName, String deptName) {
//		EmpDAO dao = new EmpDAO();
//		
//		EmpDTO empDto = null;
//		if(deptId.matches("\\d+")) {
//			empDto = new EmpDTO();
//			empDto.setDeptId(Integer.parseInt(deptId));
//			empDto.setEmpName(empName);
//			empDto.setEmail(email);
//			empDto.setJobName(jobName);
//			empDto.setDeptName(deptName);
//			
//			//DeptId는 pk
//			if(dao.searchId(empDto.getDeptId()) != null) {
//				empDto.setDeptId(-1);			// 이미 값이 있다는것을 알림
//				dao.rollback();
//				dao.close();
//				return empDto;
//			}		
//			
//			boolean isSaved = dao.insertDept(empDto);
//			if(!isSaved) {
//				dao.rollback();
//				dao.close();
//				return null;
//			}
//		}
//		dao.commit();
//		dao.close();
//		return empDto;
//	}



	// 반복적으로 사용되는 이미지 경로를 불러오는 메서드 생성
	public String getProfileImage(HttpServletRequest request, String imagePath, EmpDTO data) {
		String realPath = request.getServletContext().getRealPath("/static/img/emp/"); //서버의 실제 주소
		File file = new File(realPath + data.getEmpId() + ".png"); //파일 존재를 확인하기 위해 사용
		if(file.exists()) {
			return imagePath + data.getEmpId() + ".png";  // URL주소로 사용(C드라이브들어가면 안됨!)
		} else {
			return  imagePath + "/default.png";
		}
	}
	
	public String getProfileImage(HttpServletRequest request, String imagePath, int id) {
		EmpDTO data = new EmpDTO();
		data.setEmpId(id);
		return getProfileImage(request, imagePath, data);
	}
	
	
	public String setProfileImage(HttpServletRequest request, String param, String imagePath, EmpDTO data) throws IOException, ServletException {
		Part part = request.getPart("uploadImage");
		//추가작업 성공
		if(!part.getSubmittedFileName().isEmpty()) {
			String realPath = request.getServletContext().getRealPath(imagePath);
			part.write(realPath + data.getEmpId() + ".png");
			return realPath + data.getEmpId() + ".png";
		}
		return imagePath + "default.png";
	}

	public boolean removeId(String id) {
		EmpDAO dao = new EmpDAO();
		boolean result = dao.deleteId(Integer.parseInt(id));
		
		if(result) {
			dao.commit();
		} else {
			dao.rollback();
		}
		dao.close();
		return result;
	}
	
}	