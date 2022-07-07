package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 안에넣으면 doGet실행할때마다 반복되니 밖으로 빼서 효율을 높이자.
	private DeptService service = new DeptService(); 

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
		String page = request.getParameter("page") == null ? "1" : request.getParameter("page");
		page = page.isEmpty() ? "1" : page;
		page = page.matches("\\d+") ? page : "1";
		
		request.setAttribute("page", Integer.parseInt(page));
		
		List<DeptDTO> deptDatas = null;
		
		if(search == null) {
			deptDatas = service.getPage(page);
			request.setAttribute("pageList", service.getPageList());
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
