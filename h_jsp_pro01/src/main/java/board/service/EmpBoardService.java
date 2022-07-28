package board.service;

import java.util.*;

import board.model.EmpBoardDAO;
import board.model.EmpBoardDTO;

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

}