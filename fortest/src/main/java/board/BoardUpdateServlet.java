package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService service = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/views/common/success.jsp"; //지정하지 않았으니 아무거나 쓸것
		request.getRequestDispatcher(view).forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bId = request.getParameter("bId");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board data = new Board();
		data.setbId(Integer.parseInt(bId));
		data.setBoardTitle(title);
		data.setBoardWriter(writer);
		data.setBoardContent(content);
		
		
		boolean result = service.updateBoard(data);
		
		if(result) {
			response.sendRedirect(request.getContextPath() + "/bdetail");
		} else {
			request.setAttribute("errorMsg", "게시글 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}	
	}
}
