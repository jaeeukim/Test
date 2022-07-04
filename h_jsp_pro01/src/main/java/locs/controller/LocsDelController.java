package locs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs/del")
public class LocsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		LocsDTO data = service.getId(id);
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/locs/del.jsp";
		if(data == null) {
			System.out.println("실패!");
		}
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String locId = request.getParameter("locId");
			
			int result = service.deleteLocs(locId); // 1이면 삭제 / 0이면 삭제실패 / -1이면 삭제할거 없음
			String view = "/WEB-INF/jsp/locs/del_error.jsp";

			switch(result) {
				case 1:
					response.sendRedirect(request.getContextPath() + "/locs");
					return;
				case 0:
					request.setAttribute("error", true);
					request.setAttribute("errorMsg", "삭제할 id가 없습니다.");
					break;
				case -1:
					request.setAttribute("error", true);
					request.setAttribute("errorMsg", "삭제 실패");
					break;
			}
			request.getRequestDispatcher(view).forward(request, response);
	}
}
