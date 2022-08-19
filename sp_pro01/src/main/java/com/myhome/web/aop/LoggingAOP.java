package com.myhome.web.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAOP {
		
	private static final Logger logger = LoggerFactory.getLogger(LoggingAOP.class);
	
	// 조인포인트 설정 (첫 번째 *의 위치는 반환타입 지정 위치/ *는 모든 타입을 의미) (*Controller에서 *은 문자열을 의미)
	// @Pointcut(value="execution(public * com.myhome.web.*.controller.*Controller.getData(org.springframework.ui.Model, javax.servlet.http.HttpSession))")
	@Pointcut(value="execution(* com.myhome.web.*.controller.*Controller.*(..))")
	private void loggingControllerCut() {}
	
	@Pointcut(value="execution(* com.myhome.web.*.service.*Service.*(..))")
	private void loggingServiceCut() {}
	
	@Pointcut(value="execution(* com.myhome.web.*.model.*DAO.*(..))")
	private void loggingDaoCut() {}
	
	@Pointcut(value="loggingControllerCut() || loggingServiceCut() || loggingDaoCut()")
	private void loggingMvcCut() {}
	
	
	
	// 어드바이스 구현
	@Before(value="loggingMvcCut()") 
	public void beforeLogging(JoinPoint joinPoint) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		logger.info("[{}] {} {}", dateFormat.format(date), joinPoint.getThis().toString() ,joinPoint.getSignature().getName());
		
		for(Object arg: joinPoint.getArgs()) {
			logger.info("    {}:{}", arg.getClass().getName(), arg.toString());
		}
	}
	/*
	// after 코드는 controller가 종료되는 시점에 출력될 것
	@After(value="loggingCut()")  
	public void afterControllerLogging(Joinpoint joinPoint) throws Exception {
		logger.info("AOP 테스트 중");
	}
	*/
}
