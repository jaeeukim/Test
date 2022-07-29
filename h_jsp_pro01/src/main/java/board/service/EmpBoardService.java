package board.service;

import java.util.*;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;

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

	public void incViewCnt(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		
		boolean result = false;
		EmpBoardStatisDTO statisData = dao.selectStatis(data);	//쿠키 대신 db를 사용해서 필요한 구문
		if(statisData == null) {
			result = dao.updateViewCnt(data);
			dao.insertStatis(data);
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
	
	public void incLike(EmpBoardDTO data) {
		EmpBoardDAO dao = new EmpBoardDAO();
		boolean result = dao.updateLike(data);
		if(result) {
			data.setLike(data.getLike() + 1); 
			dao.commit();
		}else {
			dao.rollback();
		}
		dao.close();
	}

}