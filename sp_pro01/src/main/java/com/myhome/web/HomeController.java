package com.myhome.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
	private JavaMailSender javaMailSender;
	
	
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
	
	@RequestMapping(value="/question", method=RequestMethod.POST)
	public String question(String sender, String context) {
		JavaMailSender mailSender = null;
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.gmail.com"); 			senderImpl.setPort(587);
		senderImpl.setUsername("riqque530@gmail.com");  senderImpl.setPassword("ceaconastemyysub"); //생성한 웹비밀번호

		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.ssl.protocols", "TLSv1.3");
		
		senderImpl.setJavaMailProperties(prop);
		
		mailSender = senderImpl;
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		String[] to = {"riqque530@gmail.com"};	 // 받는사람
		String[] cc = {};						 // 참조
		String[] bcc = {};						 // 숨은 참조
		message.setTo(to);
		message.setCc(cc);
		message.setBcc(bcc);
		
		
		message.setSubject("[" + sender + "] 님의 문의"); 	// 제목
		message.setText(context);							// 내용
			
		mailSender.send(message);							// 전송
		return "redirect:/question";
	}
	
}
