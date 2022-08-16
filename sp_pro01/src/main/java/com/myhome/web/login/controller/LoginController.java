package com.myhome.web.login.controller;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;
import com.myhome.web.login.service.LoginService;
import com.myhome.web.login.vo.LoginVO;



@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	
	@Autowired
	private DeptService deptService;
	
	@GetMapping(value="/login")
	public String login(Model model) {
		List<DeptDTO> deptDatas = deptService.getAll();
		model.addAttribute("deptDatas", deptDatas);
		return "login/login";
	}
	
	
	// 로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, LoginVO loginVo, String url, String deptRe
			, HttpServletRequest request,HttpSession session, HttpServletResponse response) {
		logger.info("login({}, {}, {}, {})", loginVo.getEmpId(), loginVo.getDeptId(), loginVo.getEmpName(), deptRe);
		
		boolean result = service.login(session, loginVo);
		
		if(result) {
			// 로그인 성공
			Cookie cookie;
			if(deptRe != null) {
				cookie = new Cookie("deptRe", String.valueOf(loginVo.getDeptId()));
				cookie.setMaxAge(60 * 60 * 24 * 5);
			} else {
				cookie = new Cookie("deptRe", "");
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return "redirect:" + url.replaceFirst(request.getContextPath() + "/", "/");
		} else {
			// 로그인 실패
			List<DeptDTO> deptDatas = deptService.getAll();
			model.addAttribute("deptDatas", deptDatas);
			return "/login/login";
		}		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		if(session.getAttribute("loginData") != null) {
			// 기존 세션은 유지한 상태로 로그인 정보만 제거한다.
			session.removeAttribute("loginData"); //삭제
			
			// 세션을 완전히 만료시켜서 새로운 세션을 만들어지게 한다.
			// session.invalidate();
		}
		return "redirect:/index"; 
	}
	
	
	/* 값가져오기
	 * 1번
	// 가져오는 값이랑 매개변수의 값이 다르다면 @ReqeustParam을 사용해서 둘을 맞춰준다.
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model
			, @RequestParam("empId") int empId
			, @RequestParam("deptId") int deptId
			, @RequestParam("empName") String empName) {
		logger.info("empId : {}", empId);
		logger.info("deptId : {}", deptId);
		logger.info("empName : {}", empName);
		
		return "";
	}
	*/
	
	/* 2번
	// parameter값을 작성해주면 되는데 이때, 매개변수의 타입으로 자동 형변환해준다
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, int empId, int deptId, String empName) {
		logger.info("empId : {}", empId);
		logger.info("deptId : {}", deptId);
		logger.info("empName : {}", empName);
		
		return "";
	}
	*/
	/* 3번
	// getParameter로 받는 방식
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, HttpServletRequest res) {
		logger.info("empId : {}" ,res.getParameter("empId"));
		logger.info("deptId : {}" ,res.getParameter("deptId"));
		logger.info("empName : {}" ,res.getParameter("empName"));
		
		return "";
	}
	*/

}