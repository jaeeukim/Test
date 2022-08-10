package com.myhome.web.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.common.util.Paging;

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
		
		session.setAttribute("pageCount", 5);
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		}
		// List<BoardDTO> datas = service.getAll();
		
		Paging pageData = service.getPage(page, Integer.parseInt((session.getAttribute("pageCount").toString())));
		
		model.addAttribute("pageData", pageData);
		model.addAttribute("datas", pageData.getPageDatas());
		
		return "board/list";
	}
	
	// 추가 폼 요청
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String add(Model model) {
		return "";
	}
	
	// 추가 저장 요청
	// @RequestMapping(value="/add", method = RequestMethod.POST)
	// public String add(BoardVO boardVo) {
	// 	return "";
	// }
	
	
	// 수정
	// @RequestMapping(value="/modify", method = RequestMethod.POST)
	// public String modify(BoardVO boardVo) {
	// 	return "";
	// }
	
	// @RequestMapping(value="/modify", method = RequestMethod.GET)
	// public String modify(BoardVO boardVo) {
	// 	return "";
	// }
	
	
	// 삭제
}
