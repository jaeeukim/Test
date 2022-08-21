package com.myhome.web.board.controller;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
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
import org.springframework.web.multipart.MultipartFile;

import com.myhome.web.board.model.BoardDTO;
import com.myhome.web.board.service.BoardService;
import com.myhome.web.board.vo.BoardVO;
import com.myhome.web.comment.model.CommentDTO;
import com.myhome.web.comment.service.CommentService;
import com.myhome.web.common.util.Paging;
import com.myhome.web.emp.model.EmpDTO;
import com.myhome.web.upload.model.FileUploadDTO;
import com.myhome.web.upload.service.fileUploadService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	//AOP 사용으로 controller에서 logger 사용이 불필요해짐
	// private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private fileUploadService fileUploadService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model, HttpSession session
				, @RequestParam(defaultValue="1", required=false) int page //필수는아닌데 기본값은1
				, @RequestParam(defaultValue="0", required=false) int pageCount) { 	
		if(session.getAttribute("pageCount") == null) {
			session.setAttribute("pageCount", 5);			
		}
		if(pageCount > 0) {
			session.setAttribute("pageCount", pageCount);
		} 
		// List<BoardDTO> datas = service.getAll();
		
		Paging pageData = service.getPage(session, page, Integer.parseInt((session.getAttribute("pageCount").toString())));
		
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
			, HttpServletRequest request
			, HttpSession session
			, @RequestParam int id
			, @SessionAttribute("loginData") EmpDTO empDto) {
			
		BoardDTO data = service.getData(session, id);
		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);
		
		if(data == null) {
			model.addAttribute("error", "해당데이터는 존재하지 않습니다.");
			return "error/noExists";
		} else {
			service.incViewCnt(empDto, data);
			model.addAttribute("data", data);
			model.addAttribute("fileDatas", fileDatas);
			
			/* 댓글 페이징
			String page = request.getParameter("page");
			String limit = "5";
			page = page == null ? "1" : page;
			Paging commentPage = commentService.getPage(page, limit, id);
			
			model.addAttribute("commentPage", commentPage);
			*/
			
			return "board/detail";			
		}
		
	}
	
	
	// 추가 폼 요청
	@GetMapping(value="/add")
	public String add(@SessionAttribute(name="loginData", required=true) EmpDTO empDto) {
		return "board/add";
	}
	
	 // 추가 저장 요청
	 @PostMapping(value="/add")
	 public String add(HttpServletRequest request
			 , @ModelAttribute BoardVO boardVo
			 , @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			 , @RequestParam("upload") MultipartFile[] files) throws Exception {
	 	// @SessionAttribute사용으로 밑에 줄이 필요없어짐!
		// EmpDTO empDto = (EmpDTO)session.getAttribute("loginData"); 		 
		 int id = service.add(empDto, boardVo);
		 
		 if(id > 0) {
			 if(!files[0].getOriginalFilename().isEmpty()) {
				 String location = request.getServletContext().getRealPath("/resources/upload/board");
				 String url = "/resources/upload/board";  //강사님 경로는 static임 폴더명이 달라서 다르게 설정함
				 FileUploadDTO fileUploadDto = new FileUploadDTO(id, location, url);
				 int result = fileUploadService.upload(files, fileUploadDto);
			 }
			 return "redirect:/board/detail?id=" + id;			 
		 } else {
			 return "board/add";
		 }
		 
	 }
	
	
	// 수정 폼 요청
	 @GetMapping(value="/modify")
	 public String modify(Model model
			 , HttpSession session
			 , @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			 , @RequestParam int id) {
		 
		BoardDTO data = service.getData(session, id);
		List<FileUploadDTO> fileDatas = fileUploadService.getDatas(id);

		if(empDto.getEmpId() == data.getEmpId()) {
			model.addAttribute("data", data);
			model.addAttribute("fileDatas", fileDatas);
			
			return "board/modify";
		} else {			
			model.addAttribute("error", "해당 작업을 수행 할 권한이 없습니다.");
			return "error/permission";
		}
		
	 }
	
	// 수정저장 요청
	 @PostMapping(value="/modify")
	 public String modify(Model model
			 , HttpSession session
			 , @SessionAttribute(name="loginData", required=true) EmpDTO empDto
			 , @ModelAttribute BoardVO boardVo) {
		
		BoardDTO data = service.getData(session, boardVo.getId()); 
		if(empDto.getEmpId() == data.getEmpId()) {
			data.setTitle(boardVo.getTitle());
			data.setContent(boardVo.getContent());
			boolean result = service.modify(session, data);
			
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
			, HttpSession session, @RequestParam int id){
		
		BoardDTO data = service.getData(session, id);
		JSONObject json = new JSONObject();
		
		if(data == null) {
			//이미 삭제
			json.put("title", "삭제가 된 데이터");
			json.put("message", "해당 데이터는 이미 삭제가 되었습니다");
			return json.toJSONString();
		} else {
			if(data.getEmpId() == empDto.getEmpId()) {
				// 삭제
				boolean result = service.remove(session, data);
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
			, HttpSession session, @RequestParam int id) {
		BoardDTO data = service.getData(session, id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			json.put("code", "noData");
			json.put("message", "데이터가 존재하지 않습니다.");
		} else {
			try {
				service.addLike(session, empDto, data);
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
	
	// 댓글 추가 기능
	@PostMapping(value="/comment/add")
	public String commentAdd(Model model, HttpSession session
				, @SessionAttribute("loginData") EmpDTO empDto
				, @RequestParam int bid
				, @RequestParam String content) {
		
		CommentDTO data = new CommentDTO();
		data.setbId(bid);
		data.setContent(content);
		data.setEmpId(empDto.getEmpId());
		
		boolean result = commentService.add(data);
		if(result) {
			return "redirect:/board/detail?id=" + bid;			
		} else {
			session.setAttribute("commentError", "댓글 추가 작업 중 문제가 발생");
			return "redirect:/board/detail?id=" + bid;			
		}
	}
	
	// 댓글 수정 기능
	@PostMapping(value="/comment/modify", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String commendModify(Model model
					, HttpServletResponse response
					, @SessionAttribute("loginData") EmpDTO empDto
					, @RequestParam int id
					, @RequestParam String content) throws Exception {
		CommentDTO data = commentService.getData(id);

		if(empDto.getEmpId() == data.getEmpId()) {
			boolean result = commentService.modify(data);
			if(result) {
				return "redirect:/board/detail?id=" + data.getbId();
			} else {
				return "board/modify";
			}
		} else {
			model.addAttribute("error", "해당 작업을 수행 할 권한이 없습니다.");
			return "error/permissions";
		}
	}
	
	// 댓글 삭제 기능
	@PostMapping(value="/comment/delete", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String commentDelete(Model model
			, HttpServletResponse response
			, @SessionAttribute("loginData") EmpDTO empDto
			, @RequestParam int id) throws Exception {

		CommentDTO data = commentService.getData(id);
		
		JSONObject json = new JSONObject();
		
		if(data == null) {
			// 이미 삭제가 되었음.
			json.put("title", "삭제가 된 데이터");
			json.put("message", "해당 데이터는 이미 삭제가 되었습니다.");
			return json.toJSONString();
		} else {
			if(data.getEmpId() == empDto.getEmpId()) {
				// 삭제 가능
				boolean result = commentService.remove(data);
				if(result) {
					json.put("title", "삭제 완료");
					json.put("message", "삭제 처리가 완료되었습니다.");
					return json.toJSONString();
				} else {
					json.put("title", "삭제 실패");
					json.put("message", "삭제 작업중 알 수 없는 문제가 발생하였습니다.");
					return json.toJSONString();
				}
			} else {
				// 작성자 불일치 - 삭제 불가 - 권한 없음
				json.put("title", "삭제 불가");
				json.put("message", "해당 데이터를 삭제할 권한이 없습니다.");
				return json.toJSONString();
			}
		}		
	}
	 
}
