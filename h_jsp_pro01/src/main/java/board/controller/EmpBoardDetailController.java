package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.model.EmpBoardStatisDTO;
import board.service.EmpBoardService;
import emps.model.EmpDTO;
import emps.service.EmpService;


@WebServlet("/board/detail")
public class EmpBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmpBoardService service = new EmpBoardService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/detail.jsp";
		
		String id = request.getParameter("id");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		/*	테이블을 만들었기 때문에 쿠키는 주석처리
		// 좋아요 올리기 
		// 1회만 가능하게 하기 위해 작성함 
		// 쿠키 or 세션 : 게시글 번호 기록  -> 쿠키나 세션을 지워버리면 끝이니까 이경우 db에 따로 만들어서 하면 좋다
		if(data != null) {
			Cookie cookies[] = request.getCookies();
			List<String> viewList = new ArrayList<String>();
			for(Cookie c: cookies) {
				if(c.getName().equals("boardView")) {
					viewList = new ArrayList<String>(Arrays.asList(c.getValue().split("/")));
				}
			}
			
			boolean isViewed = false;
			for(String s: viewList) {
				if(s.equals(id)) {
					isViewed = true;
				}
			}
			if(!isViewed) {
				viewList.add(id);
				Cookie cookie = new Cookie("boardView", String.join("/", viewList)); 
				cookie.setMaxAge(60 * 60 * 24 * 7);  //일주일동안 유지되도록 하기
				response.addCookie(cookie);
				service.incViewCnt(data);
			}
			*/
		if(data != null) {
			service.incViewCnt(request.getSession(), data);
			EmpService empService = new EmpService();
			EmpDTO empData = empService.getId("" + data.getEmpId());
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);			
		}else {
			// 별도의 페이지로 데이타 없음을 알리기
			// 1. 포워드    forward  : url주소는 바뀌지않음
			// 2. 리다이렉트 redirect : url주소가 바뀜
		}	
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		

		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		if(data != null) {
			service.incLike(request.getSession(), data);
			sb.append(String.format("\"%s\": \"%s\",", "code", "success"));
			sb.append(String.format("\"%s\": %d", "likeCnt", data.getLike()));
		}
		sb.append("}");
		
		out.append(sb.toString());
		out.flush();
		
	}
}
