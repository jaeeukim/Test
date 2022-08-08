package com.myhome.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myhome.web.dept.model.DeptDTO;
import com.myhome.web.dept.service.DeptService;

// @Controller 작성해줘야 spring에서 bean객체로 등록이 가능하다
// 	DI  : Dependency Injection 의존성 주입 -> 외부에서 객체 생성하여 주입 (개발자주체)
// 	IoC : Inversion of Control 제어 역전  -> 객체의 호출을 외부에서 결정 (스프링주체)

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("Welcome index!");
		
		// request.setAttribute()대신 model.addAtrribute("", "")사용
		
		List<DeptDTO> deptDatas = deptService.getAll();
		
		model.addAttribute("deptDatas", deptDatas);
		
		// view (views안에 경로만 작성해주면 됨_jsp생략가능)
		// servlet-context.xml에서 정의되어있기 때문
		return "index";
	}
	
}
