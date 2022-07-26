package dept.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;
import login.model.PermDTO;

@WebServlet("/depts/del")
public class DeptDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService();
	
	//필터로 기능 이동함
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// req.getMethod(); // post냐 get이냐 구분
//
//		HttpSession session = req.getSession();
//		PermDTO perm = ((Map<String, PermDTO>)session.getAttribute("permData")).get("departments");
//		
//		if(perm.ispRead()) {
//			super.service(req, resp);			
//		} else {
//			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
//		}
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DeptDTO data = service.getId(id);
		request.setAttribute("data", data);
		
		
		String view = "/WEB-INF/jsp/dept/del.jsp";
		//이런식으로 데이터가 없는 경우의 jsp를 따로 만들어 각 jsp를 간단하게 만드는 방법도 있다.
		if(data == null) {
			view = "/WEB-INF/jsp/dept/del_no.jsp";			
		} 
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		
		int result = service.deleteDept(deptId);
		String view = "/WEB-INF/jsp/dept/del_error.jsp";
		
		switch(result) {
		case 1:
			response.sendRedirect(request.getContextPath() + "/depts");
			return;
		case 0:
			request.setAttribute("error", true);
			request.setAttribute("errorMsg", "삭제 처리 중 문제 발생");
			break;
		case -1:
			request.setAttribute("error", true);
			request.setAttribute("errorMsg", "삭제 처리 중 문제 발생");
			break;
		}
		request.getRequestDispatcher(view).forward(request, response);					
		
	}

}
