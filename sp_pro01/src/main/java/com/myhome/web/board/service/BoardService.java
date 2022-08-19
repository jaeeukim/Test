package com.myhome.web.board.service;

import java.sql.SQLDataException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhome.web.board.controller.BoardController;
import com.myhome.web.board.model.BoardDAO;
import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.model.BoardStatisDTO;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;



@Service
public class BoardService {
	// AOP에서 logger 등록되어서 불필요해짐
	// private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private BoardDAO dao;
	

	
    public BoardService() {
    }

    public List<BoardDTO> getAll(HttpSession session) {
    	List<BoardDTO> datas = dao.selectAll();
    	return datas;
    }
    
    @Transactional 	//이 메서드가 종료되기전까지 session을 유지시켜줌 (xml설정 필요)
	public Paging getPage(HttpSession session, int page, int limit) {
		int totalRows = dao.getTotalRows();
		
		Paging paging = new Paging(page, limit, totalRows);
		dao.selectPage(paging);
		return paging;
	}
	
    public BoardDTO getData(HttpSession session, int id) {    	
    	BoardDTO data = dao.selectData(id);
    	return data;
    }
    
    public int add(EmpDTO empDto, BoardVO data) {

    	BoardDTO boardDto = new BoardDTO();
    	boardDto.setTitle(data.getTitle());
    	boardDto.setContent(data.getContent());
    	boardDto.setEmpId(empDto.getEmpId());
    	
    	boolean result = dao.insertData(boardDto);
    	
    	if(result) {
    		return boardDto.getId();
    	}
    	return 0;
    }
    
	public boolean modify(HttpSession session, BoardDTO data) {
		boolean result = dao.updateData(data);
		return result;
	}
    
	@Transactional
	public boolean remove(HttpSession session, BoardDTO data) {
		dao.deleteStatisData(data);
		boolean result = dao.deleteData(data);
		
		return result;
		
	}
	
    @Transactional
	public void incViewCnt(EmpDTO empDto, BoardDTO data) {
		
		boolean result = false;
		BoardStatisDTO statisData = new BoardStatisDTO();
		statisData.setBId(data.getId());
		statisData.setEmpId(empDto.getEmpId());   //쿠키 대신 db를 사용해서 필요한 구문
		
		statisData = dao.selectStatis(statisData);
		if(statisData == null) {
			result = dao.updateViewCnt(data);
			if(!result) {
				throw new RuntimeException("BoardService.incViewCnt 조회 수 업데이트 작업중 오류 발생");
			}
			statisData = new BoardStatisDTO();
			statisData.setBId(data.getId());
			statisData.setEmpId(empDto.getEmpId());
			
			dao.insertStatis(statisData);
			if(!result) {
				throw new RuntimeException("BoardService.incViewCnt 통계 정보 추가 작업중 오류 발생");
			}
		} else {
			Date now = new Date();
			long timeDiff = now.getTime() - statisData.getLatestView().getTime();
			if(timeDiff / (1000 * 60 * 60 * 24) >= 7) { // 7일이상일때
				result = dao.updateViewCnt(data);
				if(!result) {
					throw new RuntimeException("BoardService.incViewCnt 조회수 업데이트 추가 작업중 오류 발생");
				}
				dao.updateStatis(statisData);
				if(!result) {
					throw new RuntimeException("BoardService.incViewCnt 통계 정보 업데이트 추가 작업중 오류 발생");
				}
				
			}
		}
		if(result) {
			// db의 테이블 값만 올라갔으므로 data의 viewCnt값도 올려주는 작업을진행한다.
			data.setViewCnt(data.getViewCnt() + 1); 
		}
		
		// throw new RuntimeException("RuntimeException이 발생시키면 rollback.");
		// transaction 진행중 (insert, update, delete)작업이 이루어질 때 rollback용으로 만들면 좋다 
	}

    
	public void addLike(HttpSession session, EmpDTO empDto, BoardDTO data) throws SQLDataException{
		this.incLike(empDto, data);
	}
	
	@Transactional(rollbackFor = SQLDataException.class)
	private void incLike(EmpDTO empDto, BoardDTO data) throws SQLDataException {
		// 1. EMP_BOARDS_STATISTICS 테이블에서 추천했던 기록찾음
		// 2. 찾은 기록에서 ISLIKE 컬럼의 값에 따라 다음의 작업을 진행한다
		//	 (조회해야 추천할 수 있기때문에 값을 못찾을 수 없다!!)
		// 		2-1. 찾은기록에서 ISLIKE 컬럼의 값이 N이면 추천수 +1
		//			 EMP_BOARDS 에서 추천수 +1 을 한다.
		// 		2-2. 찾은기록에서 ISLIKE 컬럼의 값이 Y이면 추천수 -1
		//			 EMP_BOARDS 에서 추천수 -1 을 한다.
		
		boolean result = false;
		BoardStatisDTO statisData = new BoardStatisDTO();
		statisData.setBId(data.getId());
		statisData.setEmpId(empDto.getEmpId());
		
		statisData = dao.selectStatis(statisData);
		
		if(statisData.isLike()) {
			// 추천했음 -> 추천수 -1
			statisData.setLike(false);
			data.setLike(data.getLike() - 1);
		} else {
			// 추천 안했음 -> 추천수 +1
			statisData.setLike(true);
			data.setLike(data.getLike() + 1);
		}
		result = dao.updateStatis(statisData, "like");
		if(!result) {
			throw new SQLDataException("BoardService.incLike - 추천 통계 업데이트 중 문제 발생");
		}
		result = dao.updateLike(data);
		if(!result) {
			throw new SQLDataException("BoardService.incLike - 추천 수 업데이트 중 문제 발생");
		}
	
	}

	/*
	 * 	

	public Paging getPage(String page, String limit, String search) {
		
		int totalRows = dao.totalRow(search);
		
		Paging paging = new Paging(Integer.parseInt(page), Integer.parseInt(limit), totalRows);
		dao.selectPage(paging, search);
		return paging;
	}
	
	public List<Integer> getPageList() {
		
		List<Integer> pageList = new ArrayList<Integer>();
		int total = dao.totalRow();
		
		for(int num = 0; num <= (total - 1) / 10; num++) {
			pageList.add(num + 1);
		}
		return pageList;
	}
	*/
	/*
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
	*/


}