package com.myhome.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
		urlPatterns = {
				"/board", "/board/*"
		}
)

//로그인 하지 않으면 정보를 볼 수없게 하는 필터 
public class LoginFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String qs = "";
		if(((HttpServletRequest)request).getQueryString() != null) {
			qs = "?" + ((HttpServletRequest)request).getQueryString();			
		}
			
		String path =((HttpServletRequest)request).getRequestURI();
		
		if(session.getAttribute("loginData") != null) {
			chain.doFilter(request, response);			
		} else {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login?url=" + path + qs);			
		}
		
	}


}
