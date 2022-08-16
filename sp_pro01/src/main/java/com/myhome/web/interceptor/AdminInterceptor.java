package com.myhome.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.myhome.web.emp.model.EmpDTO;

// 관리자용 페이지로 이동시키기
public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
	
		if(session.getAttribute("loginData") != null) {
			EmpDTO empDto = (EmpDTO)session.getAttribute("loginData");

			// 관리부(Administration)
			if(empDto.getDeptId() == 10) {
				String oldView = modelAndView.getViewName();
				if(!oldView.startsWith("redirect:")) { //리다이렉트의경우 admin/이 붙지않도록
					modelAndView.setViewName("admin/" + oldView); // 별도의 관리자 페이지가 사용되게 됨.					
				}
			}
		}
	}
}
