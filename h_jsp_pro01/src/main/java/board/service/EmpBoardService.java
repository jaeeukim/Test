package board.service;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;
import dept.model.DeptDAO;
import dept.model.DeptDTO;
import emps.model.EmpDTO;
import locs.model.LocsDAO;
import locs.model.LocsDTO;

/**
 * 
 */
public class EmpBoardService {


    public EmpBoardService() {
    }

    public int add(EmpBoardDTO data) {
    	EmpBoardDAO dao = new EmpBoardDAO();
    	
    	// 직접 스퀀스 생성할때 필요함
    	// int seq = dao.getNextSeq();
    	// data.setId(seq);
    	
    	boolean result = dao.insertData(data);
    	
    	if(result) {
    		dao.commit();
    		dao.close();
    		return data.getId();
    	}
    	dao.rollback();
    	dao.close();
        return 0;
    }

	public EmpBoardDTO getData(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		EmpBoardDTO data = dao.selectData(id);
		
		dao.close();
		return data;
	}

	public void incViewCnt(HttpSession session, EmpBoardDTO data) {
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = false;
		EmpBoardStatisDTO statisData = new EmpBoardStatisDTO();
		statisData.setId(data.getId());
		statisData.setEmpId(empData.getEmpId());   //쿠키 대신 db를 사용해서 필요한 구문
		
		statisData = dao.selectStatis(statisData);
		if(statisData == null) {
			result = dao.updateViewCnt(data);
			statisData = new EmpBoardStatisDTO();
			statisData.setBId(data.getId());
			statisData.setEmpId(empData.getEmpId());
			
			dao.insertStatis(statisData);
		} else {
			Date now = new Date();
			long timeDiff = now.getTime() - statisData.getLatestView().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) { // 7일이상일때
				result = dao.updateViewCnt(data);
				dao.updateStatis(statisData);
			}
		}
		if(result) {
			// db의 테이블 값만 올라갔으므로 data의 viewCnt값도 올려주는 작업을진행한다.
			data.setViewCnt(data.getViewCnt() + 1); 
			dao.commit();
		}else {
			dao.rollback();
		}
		dao.close();
	}
	
	public void incLike(HttpSession session, EmpBoardDTO data) {
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");
		EmpBoardDAO dao = new EmpBoardDAO();		
		
		// 1. EMP_BOARDS_STATISTICS 테이블에서 추천했던 기록찾음
		// 2. 찾은 기록에서 ISLIKE 컬럼의 값에 따라 다음의 작업을 진행한다
		//	 (조회해야 추천할 수 있기때문에 값을 못찾을 수 없다!!)
		// 		2-1. 찾은기록에서 ISLIKE 컬럼의 값이 N이면 추천수 +1
		//			 EMP_BOARDS 에서 추천수 +1 을 한다.
		// 		2-2. 찾은기록에서 ISLIKE 컬럼의 값이 Y이면 추천수 -1
		//			 EMP_BOARDS 에서 추천수 -1 을 한다.
		
		boolean result = false;
		EmpBoardStatisDTO statisData = new EmpBoardStatisDTO();
		statisData.setId(data.getId());
		statisData.setEmpId(empData.getEmpId());
		
		statisData = dao.selectStatis(statisData);
		
		System.out.println(statisData.getBId());
		System.out.println(statisData.isLike());
		if(statisData.isLike()) {
			// 추천했음 -> 추천수 -1
			statisData.setLike(false);
			data.setLike(data.getLike() - 1);
		} else {
			// 추천 안했음 -> 추천수 +1
			statisData.setLike(true);
			data.setLike(data.getLike() + 1);
		}
		dao.updateStatis(statisData, "like");
		result = dao.updateLike(data);
		
		
		if(result) {
			dao.commit();
		}else {
			dao.rollback();
		}
		dao.close();
	}

	public List<EmpBoardDTO> getPage(int pageNumber) {
		EmpBoardDAO dao = new EmpBoardDAO();		
		int start, end;
		start = (pageNumber - 1) * 10;
		end = start + 10;
	
		List<EmpBoardDTO> datas = dao.searchPage(start, end);
		dao.close();
		return datas;
	}

	public List<Integer> getPageList() {
		EmpBoardDAO dao = new EmpBoardDAO();		
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / 10; num++) {
			pageList.add(num + 1);
		}
		return pageList;
	}
	
	public EmpBoardDTO getId(int id) {
		EmpBoardDAO dao = new EmpBoardDAO();		
				
		EmpBoardDTO data = dao.searchId(id);
		
		dao.close();
		return data;
	}
	
	public List<EmpBoardDTO> getAll() {
		EmpBoardDAO dao = new EmpBoardDAO();		
		List<EmpBoardDTO> datas = dao.searchAll();
		dao.close();
		return datas;
	}
	

}