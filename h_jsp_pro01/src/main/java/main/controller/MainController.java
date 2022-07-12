package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String view = "/WEB-INF/jsp/index.jsp";
       
	DeptService deptService = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 저장한 세션을 가져옴
		HttpSession session = request.getSession();
		 
		RequestDispatcher rd = null;
		// 만약 세션으로 가져온 속성이 비어있다면 (로그인 X)
		if(session.getAttribute("loginData") == null) {
			List<DeptDTO> deptList =deptService.getAll();
			request.setAttribute("deptList", deptList);			
			rd = request.getRequestDispatcher(view);
		} else {
			rd = request.getRequestDispatcher("/WEB-INF/jsp/index2.jsp");
			
		}
	
		rd.forward(request, response);
		
		
		
	}

}
