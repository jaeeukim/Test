package emps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dept.service.DeptService;
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.service.JobService;


@WebServlet("/emps/modify")
public class EmpModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String view = "/WEB-INF/jsp/emps/modify.jsp";
    EmpService empService = new EmpService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id파라미터 추출 후 변수 id에 저장
		String id = request.getParameter("id");
		
		// 변수 id에 저장된 값으로 직원 조회
		EmpService empService = new EmpService();
		DeptService deptService = new DeptService();
		JobService jobService = new JobService();
		
		EmpDTO data = empService.getId(id);
		EmpDetailDTO dataDetail = empService.getDetail(data.getEmpId());
		
		// 조회한 데이터를 JSP에서 사용할 수 있게 request 객체의 속성으로 저장
		request.setAttribute("data", data);
		request.setAttribute("dataDetail", dataDetail);
		request.setAttribute("deptDatas", deptService.getAll());
		request.setAttribute("jobDatas", jobService.getAll());
		
		
		// 프로필 사진이 /static/img/emp/ 디렉터리에 직원 ID로 저장되어 있는 경우
		// 프로필 사진의 경로를 request객체의 속성으로 저장(단, 없으면 기본 사진)
		String imagePath = empService.getProfileImage(request, "/static/img/emp/", data);
		request.setAttribute("imagePath", imagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String jobId = request.getParameter("jobId");
		String deptId = request.getParameter("deptId");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hireDate");
		String phone = request.getParameter("phone");
		String salary = request.getParameter("salary");
		String commission = request.getParameter("commission");
		
	    EmpService empService = new EmpService();
		
		EmpDTO empData = empService.getId(empId); // 추가와 달리 새로운 객체를 생성하지않고 값을 가져온다
		if(empData == null) {
			// 해당 데이터는 존재하지 않습니다.
			request.getSession().setAttribute("error", "해당 데이터는 존재하지 않습니다");
			response.sendRedirect(request.getContextPath() + "/error");
			return;
		}
		empData.setEmpName(empName);
		empData.setJobId(jobId);
		empData.setDeptId(deptId);
		empData.setEmail(email);
		
		EmpDetailDTO empDetailData = empService.getDetail(empData.getEmpId());
		if(empDetailData == null) {
			// 해당 데이터는 존재하지 않습니다.
			request.getSession().setAttribute("error", "해당 데이터는 존재하지 않습니다");
			response.sendRedirect(request.getContextPath() + "/error");
			return;
		}
		empDetailData.setEmpId(empId);
		empDetailData.setHireDate(hireDate);
		empDetailData.setPhone(phone);
		empDetailData.setSalary(salary);
		empDetailData.setCommission(commission);
		
		boolean result = empService.setEmployee(empData, empDetailData);
		
		if(result) {			
			//추가작업 성공
			String imagePath = empService.setProfileImage(request, "uploadImage", "/static/img/emp/", empData);
			// part.write(realPath + empData.getEmpId() + ".png");

			response.sendRedirect(request.getContextPath() + "/emps/detail?id=" + empData.getEmpId());			

		} else {
			doGet(request, response);
		}
	}

}
