package myInfo.controller;

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
import emps.model.EmpDTO;
import emps.model.EmpDetailDTO;
import emps.service.EmpService;
import job.model.JobDTO;
import job.service.JobService;


@WebServlet("/myInfo")
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
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);			
		}
		
	}


}
