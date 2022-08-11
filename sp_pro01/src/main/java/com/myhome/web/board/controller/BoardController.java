package com.myhome.web.board.controller;

import java.sql.SQLDataException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model, HttpSession session
				, @RequestParam(defaultValue="1", required=false) int page //필수는아닌데 기본값은1
				, @RequestParam(defaultValue="0", required=false) int pageCount) { 
		logger.info("getData(page={}, pageCount={})", page, pageCount);
		
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5);			
		}
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		} 
		// List<BoardDTO> datas = service.getAll();
		
		Paging pageData = service.getPage(page, Integer.parseInt((session.getAttribute("pageCount").toString())));
		
		model.addAttribute("pageData", pageData);
		model.addAttribute("datas", pageData.getPageDatas());
		
		return "board/list";
	}
	
	// 조회 상세
	/* 
	// detail?page=id값 형식이 아닌 /detail/id값으로 가능하게함
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
		public String getDetail(Model model
				, @PathVariable int id) {
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String getDetail(Model model
			, @RequestParam int id
			, @SessionAttribute("loginData") EmpDTO empDto) {
			
		BoardDTO data = service.getData(id);
		
		if(data == null) {
			model.addAttribute("error", "해당데이터는 존재하지 않습니다.");
			return "error/noExists";
		} else {
			service.incViewCnt(empDto, data);
			model.addAttribute("data", data);
			return "board/detail";			
		}
		
	}
	
	
	// 추가 폼 요청
	@GetMapping(value="/add")
	public String add(@SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
		logger.info("add({})", empDto);

		return "board/add";
	}
	
	 // 추가 저장 요청
	 @PostMapping(value="/add")
	 public String add(@ModelAttribute BoardVO boardVo
			 , @SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
	 	// @SessionAttribute사용으로 밑에 줄이 필요없어짐!
		// EmpDTO empDto = (EmpDTO)session.getAttribute("loginData"); 
		 logger.info("add(boardVo={}, empDto={})", boardVo, empDto);
		 int id = service.add(empDto, boardVo);
		 
		 if(id > 0) {
			 return "redirect:/board/detail?id=" + id;			 
		 } else {
			 return "board/add";
		 }
		 
	 }
	
	
	// 수정 폼 요청
	 @GetMapping(value="/modify")
	 public String modify(Model model
			 , @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			 , @RequestParam int id) {
		 
		BoardDTO data = service.getData(id);
		if(empDto.getEmpId() == data.getEmpId()) {
			model.addAttribute("data", data);
			
			return "board/modify";
		} else {			
			model.addAttribute("error", "해당 작업을 수행 할 권한이 없습니다.");
			return "error/permission";
		}
		
	 }
	
	// 수정저장 요청
	 @PostMapping(value="/modify")
	 public String modify(Model model
			 , @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			 , @ModelAttribute BoardVO boardVo) {
		
		BoardDTO data = service.getData(boardVo.getId()); 
		if(empDto.getEmpId() == data.getEmpId()) {
			data.setTitle(boardVo.getTitle());
			data.setContent(boardVo.getContent());
			boolean result = service.modify(data);
			
			if(result) {
				return "redirect:/board/detail?id=" + data.getId();
			} else {
				return "board/modify";
			}
		} else {
			model.addAttribute("error", "해당 작업을 수행 할 권한이 없습니다.");
			return "error/permissions";
		}
	 }
	
	
	// 삭제
	@SuppressWarnings("unchecked")
	@PostMapping(value="/delete", produces = "application/json; charset=UTF-8")
	@ResponseBody //ajax로 인해 responsebody와 produces가 필요함
	public String delete(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id){
		
		BoardDTO data = service.getData(id);
		JSONObject json = new JSONObject();
		
		if(data == null) {
			//이미 삭제
			json.put("title", "삭제가 된 데이터");
			json.put("message", "해당 데이터는 이미 삭제가 되었습니다");
			return json.toJSONString();
		} else {
			if(data.getEmpId() == empDto.getEmpId()) {
				// 삭제
				boolean result = service.remove(data);
				if(result) {
					// 삭제 성공
					json.put("title", "삭제 완료");
					json.put("message", "삭제 처리가 완료되었습니다.");
					return json.toJSONString();
					
				}else {
					// 삭제 실패
					json.put("title", "삭제 실패");
					json.put("message", "삭제 작업 중 알 수 없는 오류가 발생하였습니다.");
					return json.toJSONString();
				}
				
			}else {
				// 작성자 불일치 - 권한없음
				json.put("title", "삭제 불가");
				json.put("message", "해당 데이터를 삭제할 권한이 없습니다.");
				return json.toJSONString();
			}
		}
	
	}
	
	
	// 좋아요
	@PostMapping(value="/like", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String like(@SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) {
		BoardDTO data = service.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			json.put("code", "noData");
			json.put("message", "데이터가 존재하지 않습니다.");
		} else {
			try {
				service.incLike(empDto, data);
				json.put("code", "noData");
				json.put("message", "데이터 처리가 완료되었습니다.");
				json.put("likeCnt", data.getLike());				
			} catch (SQLDataException e){
				json.put("code", "fail");
				json.put("likeCnt", "오류");				
				json.put("message", "데이터 처리중 문제가 발생했습니다.");
			}
		}
		return json.toJSONString();
	}

	 
}
