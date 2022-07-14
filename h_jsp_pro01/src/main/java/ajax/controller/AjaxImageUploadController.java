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


@WebServlet("/ajax/imageUpload")
@MultipartConfig
public class AjaxImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");

		Part part = request.getPart("uploadImage"); //input이 file인 요소의 name씀 
		response.setContentType("application/json; charset=utf-8");
		if(!part.getSubmittedFileName().isEmpty()) {	// 업로드가 된 이미지가 있는지 체크
			String realPath = request.getServletContext().getRealPath("/static/img/emp/"); //서버의 실제 주소 (좌측화면에는 없어도 서버주소로 들어가면 저장되어있음)

			// 저장은 마지막에 실행한다. (test 하고 마지막에 주석 풀어주기)
			part.write(realPath + empData.getEmpId() + ".png"); // 만약 변경해도 사진 안뜨면 f12 network에서 cache체크 해주기
			System.out.println(realPath);
			// 쓰는 방식이 헷갈린다면 메모장에 쓰고 ""안에 붙여넣기 하자 
			out.println("{");
			out.println("	\"msg\": \"저장성공\","); // 속성명 : 속성값
			out.println("    \"loc\": \"./static/img/emp/" + empData.getEmpId() + ".png\""); //강사님과 다르게 ./static했으니 잊지말기!!!!!!!!
			out.println("}");
						
		} else {
			out.println("{");
			out.println("	\"msg\": \"저장실패\",");
			out.println("	\"loc\": \"/static/img/emp/default.png\"");
			out.println("}");
			
		}
		out.flush();
//		Part part = request.getPart("uploadImage");
//		
//		System.out.println(part.getSubmittedFileName());
	}

}
