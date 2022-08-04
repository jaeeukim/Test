package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import emps.model.EmpDTO;

@WebServlet("/image/upload")
@MultipartConfig
public class CKEditorImageControlller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//CKEditor 이미지용
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");

		Part part = request.getPart("upload");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		if(!part.getSubmittedFileName().isEmpty()) {	// 업로드가 된 이미지가 있는지 체크
			String realPath = request.getServletContext().getRealPath("/static/img/board/"); //서버의 실제 주소 (좌측화면에는 없어도 서버주소로 들어가면 저장되어있음)
			System.out.println(realPath);
			part.write(realPath + part.getSubmittedFileName());
			
			sb.append(String.format("\"%s\": %d, ", "uploaded", 1));
			sb.append(String.format("\"%s\": \"%s\", ", "fileName", part.getSubmittedFileName()));
			sb.append(String.format("\"%s\": \"%s\"  ", "url", "/jsp01/static/img/board/" + part.getSubmittedFileName()));
			
			
			
			/* out사용한 방법
			out.println("{");
			out.println("	\"uploaded\": 1,"); // 속성명 : 속성값
			out.println("    \"fileName\": \"" + part.getSubmittedFileName() + "\",");
			out.println("    \"loc\": \"./static/img/emp/" + part.getSubmittedFileName()+ "\""); 
			out.println("}");
			*/		
		}
		sb.append("}");
		
		out.append(sb);
		out.flush();
	}

}
