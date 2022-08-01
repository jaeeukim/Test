package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;


@WebServlet("/board/mod")
public class EmpBoardModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmpBoardService service = new EmpBoardService();   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/mod.jsp";
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		EmpBoardDTO data = service.getData(Integer.parseInt(id));


		String view = "/WEB-INF/jsp/board/mod.jsp";
		
		request.getRequestDispatcher(view).forward(request, response);
	}

}
