package filter.url;

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

@WebFilter("/*")
public class UrlFilter extends HttpFilter implements Filter {
	// 모든페이지마다 사용자가 요청을하면 filter를 거치게 되고
	// 이 filter를 거쳐서 servlet으로 이동 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String url = ((HttpServletRequest)request).getRequestURI();
		request.setAttribute("url", url);
		chain.doFilter(request, response);
	}


}
