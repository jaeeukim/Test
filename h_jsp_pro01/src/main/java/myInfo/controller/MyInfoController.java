package myInfo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;


@WebServlet("/myInfo")
@MultipartConfig
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "/WEB-INF/jsp/login/myInfo.jsp";
	
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	private EmpService empService = new EmpService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			EmpDTO empData = (EmpDTO) session.getAttribute("loginData");
			
			EmpDetailDTO empDetail = empService.getDetail(empData.getEmpId());
			List<DeptDTO> deptDatas = deptService.getAll();
			List<JobDTO> jobDatas = jobService.getAll();
			
			request.setAttribute("empDetail", empDetail);
			request.setAttribute("deptDatas", deptDatas);
			request.setAttribute("jobDatas", jobDatas);
			
			// 로그인을 한 사원의 이미지 /static/img/emp/사원ID.png가 있는지 확인 후
			// 있으면 사원ID.png를 사용, 없으면 default.png를 사용한다.
			String imagePath = empService.getProfileImage(request, "/static/img/emp/", empData);
			request.setAttribute("imagePath", imagePath);
			
			/* empService에 몰아 넣었음
			String realPath = request.getServletContext().getRealPath("/static/img/emp/"); //서버의 실제 주소
			File file = new File(realPath + empData.getEmpId() + ".png"); //파일 존재를 확인하기 위해 사용

			
			if(file.exists()) {
				request.setAttribute("imagePath", "/static/img/emp/" + empData.getEmpId() + ".png");  // URL주소로 사용(C드라이브들어가면 안됨!)
			} else {
				request.setAttribute("imagePath", "/static/img/emp/default.png");
			}
			*/
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpDTO empData = (EmpDTO)session.getAttribute("loginData");

		
		// String imgFileName = part.getSubmittedFileName();  // img 파일 명
		// long imgSize = part.getSize(); // byte단위로 사이즈 알려줌
			
		
		String email = request.getParameter("email"); //EmpDTO에 저장된 정보
		String phone = request.getParameter("phone"); //EmpDetailDTO에 저장된 정보
		
		
		int empId = ((EmpDTO)session.getAttribute("loginData")).getEmpId();
		EmpDTO updateEmpData = new EmpDTO();
		updateEmpData.setEmpId(empId);
		updateEmpData.setEmail(email);
		
		EmpDetailDTO updateEmpDetailData = new EmpDetailDTO();
		updateEmpDetailData.setEmpId(empId);
		updateEmpDetailData.setPhone(phone);
		
		
		boolean result = empService.setEmployee(updateEmpData, updateEmpDetailData);
		
		if(result) {
			// 수정 작업이 성공했을때에만 업로드 되도록
			Part part = request.getPart("uploadImage"); //input이 file인 요소의 name씀 
			
			if(!part.getSubmittedFileName().isEmpty()) {	// 업로드가 된 이미지가 있는지 체크
				String realPath = request.getServletContext().getRealPath("/static/img/emp/"); //서버의 실제 주소 (좌측화면에는 없어도 서버주소로 들어가면 저장되어있음)
				part.write(realPath + empData.getEmpId() + ".png"); // 만약 변경해도 사진 안뜨면 f12 network에서 cache체크 해주기 
			}
			response.sendRedirect(request.getContextPath() + "/logout"); // 로그아웃되었다고 알리는 페이지
			session.invalidate();
		} else {
			doGet(request, response);
		}
		
	}
}
