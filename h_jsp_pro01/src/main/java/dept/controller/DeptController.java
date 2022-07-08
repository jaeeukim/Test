package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.Parameter;
import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 안에넣으면 doGet실행할때마다 반복되니 밖으로 빼서 효율을 높이자.
	private DeptService service = new DeptService(); 
	private Parameter param = new Parameter();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		/* 숫자아닌 다른 타입이 들어온 경우 -> matches로 한줄정리 가능한
		boolean isNumber = true;
		for(int i = 0; i < search.length(); i++) {
			char c = search.charAt(i);
			if(!(c >= '0' && c <= '9')) {
				isNumber = false;
				break;
			}
		}
		*/
		int page = param.defaultIntValue(request, "page", "1");		
		int pageCount = 0;
		
		
		boolean pageCountCookieExist = false;
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c: cookies) {
			if(c.getName().equals("pageCount")) {
				pageCount = Integer.parseInt(c.getValue());
				pageCountCookieExist = true;
			}
		}
		
		// 변경이 이루워졌는지 || 쿠키가 존재하지 않는지 체크
		if(request.getParameter("pgc") != null || !pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
			
		// 쿠키 재설정 
		Cookie cookie = new Cookie("pageCount", String.valueOf(pageCount));  //순서대로 쿠키이름, 쿠키값
		response.addCookie(cookie);
				
		List<DeptDTO> deptDatas = null;
		if(search == null) {
			deptDatas = service.getPage(page, pageCount);
			request.setAttribute("pageList", service.getPageList(pageCount));
		} else {
			boolean isNumber = search.matches("\\d+");
			if(isNumber) {
				DeptDTO data = service.getId(search);
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>();
					deptDatas.add(data);
				}
			}
		
//		if(search == null) {
//			int page = 1;
//			if(request.getParameter("page") == null) {
//				deptDatas = service.getPage(page);				
//			} else if(request.getParameter("page").isEmpty()) {
//				deptDatas = service.getPage(page);
//			} else {
//				if(request.getParameter("page").matches("\\d+")) {
//					page = Integer.parseInt(request.getParameter("page"));
//				}
//				deptDatas = service.getPage(page);					
//			}
//			request.setAttribute("pageList", service.getPageList());
//		} else {
//			boolean isNumber = search.matches("\\d+"); //정규표현식
//			if(isNumber) {
//				DeptDTO data = service.getId(search); //얘는 DTO형식
//				if(data != null) {
//					deptDatas = new ArrayList<DeptDTO>();
//					deptDatas.add(data);				
//				}				
//			}
			
			
			
			/* DeptService에서 getId 매개변수타입이 DeptDTO일 경운
			DeptDTO dto = new DeptDTO();
			dto.setDeptId(Integer.parseInt(search));
			DeptDTO data = service.getId(dto);
			*/
		}
		
		
		//request객체에다가 속성을 설정한다. ->이후 forward할때 request 객체가 같이 전달됨
		 request.setAttribute("deptDatas", deptDatas);
		// setAttribute로 전달되면 object로 업캐스팅이됨
		
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
