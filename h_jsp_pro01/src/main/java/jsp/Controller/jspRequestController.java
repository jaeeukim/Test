package jsp.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jsp_request")
public class jspRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/jsp_request.jsp";
		
		//getParameter()
		System.out.println("param_name : " + request.getParameter("param_name"));
		
		//getParameterValues()
		if(request.getParameterValues("param_chk") != null) {
			System.out.println("param_chk : " + Arrays.asList(request.getParameterValues("param_chk")));
			List<String> strA = Arrays.asList(request.getParameterValues("param_chk"));
			System.out.println(strA);
		}
		
		
		//getParameterNames()
		Iterator<String> iter = request.getParameterNames().asIterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String view = "/WEB-INF/jsp/jsp_request.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
