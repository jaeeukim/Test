package com.mytest.mail.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAOP {
	@AfterThrowing(pointcut="within(kr.co.khi.dao.*)" +
			            "|| within(kr.co.khi.controller.*)" +
			            "|| within(kr.co.khi.service.*)", throwing="ex")
	
			    public void exmethod(JoinPoint jp, Exception ex) throws Exception {
			     
			        SimpleEmail email = new SimpleEmail();
			        email.setCharset("UTF-8");      // 한글처리
			        email.setHostName("localhost"); // SMTP (보내는 메일서버) 세팅
			        // email.setSmtpPort(25);   //원래 25번이 SMTP 포트
			        email.setFrom("보내는사람이메일주소", "닉네임");
			        // addTo(받는사람), addCc(참조), addBcc(숨은참조)
			        email.addTo("받는사람이메일주소", "넌누구냐");
			        email.addCc("참조받는사람이메일주소", "이건또모지");
			        email.setSubject("홈페이지에서 오류가 발생하였습니다.");
			        email.setMsg("내용 : "+jp.toLongString()+ex.getStackTrace().toString());
			        email.send();
			        
			    }
		
}
