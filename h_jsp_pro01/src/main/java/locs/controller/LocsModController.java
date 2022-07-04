package locs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs/mod")
public class LocsModController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		LocsDTO data = service.getId(id);
		
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/locs/mod.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locId = request.getParameter("locId");
		String streetAd = request.getParameter("streetAd");
		String posCode = request.getParameter("posCode");
		String city = request.getParameter("city");
		String staPro = request.getParameter("staPro");
		String ctrId = request.getParameter("ctrId");
		
		LocsDTO data = new LocsDTO();
		data.setLocId(Integer.parseInt(locId));
		data.setStreetAd(streetAd);
		data.setPosCode(posCode);
		data.setCity(city);
		data.setStaPro(staPro);
		data.setCtrId(ctrId);
		
		String view = "/WEB-INF/jsp/locs/mod.jsp";
		request.setAttribute("data", data);
		
		int result = service.modifyLocs(data);
		// -1이면 fk없는거 / 1이면 commit된거 / 0이면 실패한거 
		switch(result) {
			case -1 :
				request.setAttribute("errorMsg", "존재하지 않는 나라ID입니다.");
				request.getRequestDispatcher(view).forward(request, response);
				break;
			case 0:
				request.setAttribute("errorMsg", "처리과정 중 오류 발생");
				request.getRequestDispatcher(view).forward(request, response);
				break;
			case 1:
				response.sendRedirect(request.getContextPath() + "/locs?search=" + data.getLocId());
				break;
		
		}
	}
}
