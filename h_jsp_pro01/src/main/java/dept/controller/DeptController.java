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
import javax.servlet.http.HttpSession;

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
		
		// 세션 (보안성 보완) - 서버에 저장
		HttpSession session = request.getSession();
		boolean pageCountCookieExist = false;

		
		if(session.getAttribute("pageCount") != null) {
			pageCount = Integer.parseInt(session.getAttribute("pageCount").toString());
			pageCountCookieExist = true;
		} // 세션에 저장된 정보를 가져온다.
		
		if(request.getParameter("pgc") != null || pageCountCookieExist) {
			pageCount = param.defaultIntValue(request, "pgc", "10");
		}
		
		session.setAttribute("pageCount", pageCount);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		
		/*
		session.setAttribute("pageCount", page); // 세션 설정
		session.removeAttribute("pageCount"); // 세션에 설정한 pageCount 제거
		session.setMaxInactiveInterval(pageCount); // (초단위) 서버에 저장되는 세션에 시간을 지정가능
		session.invalidate(); // 세션을 만료시켜 새로운 세션을 만들 수 있게 한다.
		
		request.getSession(true); // 유효한 세션이 없는 경우 새로 만들고, 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
								  // 위에 선언과 같다 (true쓰는것과 생략한것)
		request.getSession(false); // 유효한 세션이 없는 경우 null을 반환, 유효한 세션이 있는 경우 해당 세션 정보를 가져온다.
		*/
		
		/*  쿠키 (보안성낮음) - 클라이언트에 저장
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
		
		// 쿠키에 제한을 줄 수 있다
		cookie.setMaxAge(60*60*24*12);  // 초단위로 유지시간을 지정할 수 있다. -1이면 무한 : session / 0이면 즉시 만료
		cookie.setPath("/"); //특정경로의 하위에 쿠키가 적용되도록 하는 것
		
		response.addCookie(cookie);
			
		*/
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
