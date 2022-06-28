package dept.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 안에넣으면 doGet실행할때마다 반복되니 밖으로 빼서 효율을 높이자.
	private DeptService service = new DeptService(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DeptDTO> deptDatas = service.getAll();
		
		//request객체에다가 속성을 설정한다. ->이후 forward할때 request 객체가 같이 전달됨
		request.setAttribute("deptDatas", deptDatas);
		// setAttribute로 전달되면 object로 업캐스팅이됨
		
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
