package locs.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs/add")
public class LocsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/locs/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locId = request.getParameter("locId");
		String streetAd = request.getParameter("streetAd");
		String posCode = request.getParameter("posCode");
		String city = request.getParameter("city");
		String staPro = request.getParameter("staPro");
		String ctrId = request.getParameter("ctrId");
		
		LocsDTO data = service.addLocs(locId, streetAd, posCode, city, staPro, ctrId);
	
		String view = "/WEB-INF/jsp/locs/add.jsp";
		if(data != null) {
			if(data.getLocId() == -1) { //중복된 값이 있을때 (pk임) 
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "지역ID 중복");
				request.getRequestDispatcher(view).forward(request, response);
			} else if(data.getCtrId() == null) {
				request.setAttribute("error", data);
				request.setAttribute("errorMsg", "해당 도시ID 없음");
				request.getRequestDispatcher(view).forward(request, response);				
			} else {
				response.sendRedirect(request.getContextPath() + "/locs?search=" + data.getLocId());
			}
		}else {
			request.getRequestDispatcher(view).forward(request, response);
		}
	}
}
