package dept.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;


@WebServlet("/depts/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/dept/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		DeptDTO data =  service.addDept(deptId, deptName, mngId, locId);
		String view = "/WEB-INF/jsp/dept/add.jsp";
		if(data != null) {
			if(data.getDeptId() == -1) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "부서코드 중복");
				request.getRequestDispatcher(view).forward(request, response);
			} else if(data.getMngId() == -1) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 관리자 ID 없음");
				request.getRequestDispatcher(view).forward(request, response);
			} else if(data.getLocId() == -1 ) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 지역 ID 없음");
				request.getRequestDispatcher(view).forward(request, response);				
			} else {
				// 성공 후 리다이렉트를 사용하여 페이지 이동
				// 1번 -> 다시 이전화면으로
					// response.sendRedirect("/jsp01/depts/");
				
				// 2번 -> 추가한 결과 화면
				/* response.sendRedirect("/jsp01/depts?search=" + data.getDeptId());							
					-> 위에있는 애는 servlet의 contextpath가 변경되었을때 오류 발생할수 있다  
					   contextpath를 /로 지정하면 생략해서 검색도 가능하다.*/
				
				//절대경로일때는 request.getContextPath()를 활용하자!
				response.sendRedirect(request.getContextPath() +  "/depts?search=" + data.getDeptId());							
			}
		} else {
			// 실패 후 기존 페이지를 유지하면서 사용자가 입력했던 데이터도 최대한 보존
			request.getRequestDispatcher(view).forward(request, response);
			
		}		
	}
	/*
	 * 사용자로부터 전달 받은 데이터를 service에 전달하고
	 * service 는 전달받은 데이터를 dao에 전달하고
	 * service에서는 필요한 경우 전달받은 데이터에 대한 비지니스 로직을 수행 후 dao에 전달 할 수 있다.
	 * dao에서는 전달받은 데이터를 처리하기에 적절한 SQL 구문을 찾아서 처리 할 수 있게 한다.
	 */
}
