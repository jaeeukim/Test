package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/boardAdd")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService service = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/views/common/success.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardTitle = request.getParameter("boardTitle");
		String boardWriter = request.getParameter("boardWriter");
		String boardContent = request.getParameter("boardContent");
		
		String view = "/WEB-INF/views/common/error.jsp";
		
		Board data = service.insertBoard(boardTitle, boardWriter, boardContent);
		
		if(data == null) {
			request.setAttribute("message", "게시글 등록 실패!");			
			request.getRequestDispatcher(view).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/board/boardList");
		}
		
	}

}
