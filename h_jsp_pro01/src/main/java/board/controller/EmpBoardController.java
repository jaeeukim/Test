package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import locs.model.LocsDTO;

@WebServlet("/board")
public class EmpBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	String search = request.getParameter("search");
		
		List<EmpBoardDTO> boardDatas = null;
//		if(search == null) {
//			int page = 1;
//			if(request.getParameter("page") == null) {
//				boardDatas = service.getPage(page);
//			} else if(request.getParameter("page").isEmpty()) {
//				boardDatas = service.getPage(page);
//			} else {
//				if(request.getParameter("page").matches("\\d+")) {
//					page = Integer.parseInt(request.getParameter("page"));					
//				}
//				boardDatas = service.getPage(page);
//			}
//			request.setAttribute("pageList", service.getPageList());
//		} else {
//			boolean isNumber = search.matches("\\d+");
//			if(isNumber) {
//				EmpBoardDTO data = service.getId(Integer.parseInt(search));
//				if(data != null) {
//					boardDatas = new ArrayList<EmpBoardDTO>();
//					boardDatas.add(data);
//				}
//			}
//		}

		boardDatas = new ArrayList<EmpBoardDTO>();
		
		boardDatas.addAll(service.getAll());
		
		
		
		request.setAttribute("boardDatas", boardDatas);
	
		String view = "/WEB-INF/jsp/board/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	
	

}
