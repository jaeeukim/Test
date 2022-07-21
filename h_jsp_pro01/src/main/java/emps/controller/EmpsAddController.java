package emps.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/emps/add")
@MultipartConfig
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService empService = new EmpService();
	private DeptService deptService = new DeptService();
	private JobService jobService = new JobService();
	private String view = "/WEB-INF/jsp/";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		List<DeptDTO> deptDatas = deptService.getAll();
		List<JobDTO> jobDatas = jobService.getAll();
		
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		
		String realPath = request.getServletContext().getRealPath("/static/img/emp/");
		request.setAttribute("imagePath", "/static/img/emp/default.png");
		
		RequestDispatcher rd = request.getRequestDispatcher(view + "emps/add.jsp");
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
		
		System.out.println(empId);
		System.out.println(empName);
		System.out.println(jobId);
		System.out.println(deptId);
		
		EmpDTO empData = new EmpDTO();
		empData.setEmpId(empId);
		empData.setEmpName(empName);
		empData.setJobId(jobId);
		empData.setDeptId(deptId);
		empData.setEmail(email);
		
		EmpDetailDTO empDetailData = new EmpDetailDTO();
		empDetailData.setEmpId(empId);
		empDetailData.setHireDate(hireDate);
		empDetailData.setPhone(phone);
		empDetailData.setSalary(salary);
		empDetailData.setCommission(commission);
		
		boolean result = empService.add(empData, empDetailData);
		
		if(result) {
			//추가작업 성공
			Part part = request.getPart("uploadImage");
			
			if(!part.getSubmittedFileName().isEmpty()) {
				String realPath = request.getServletContext().getRealPath("/static/img/emp/");
				part.write(realPath + empData.getEmpId() + ".png");
			}
			
			response.sendRedirect(request.getContextPath() + "/emps");
//			response.sendRedirect(request.getContextPath() + "/emps/detail?id=" + empData.getEmpId());
		} else {
			doGet(request, response);
		}
		
		
		/*
		List<EmpDTO> empDatas = empService.getEmpAll();
		request.setAttribute("empDatas", empDatas);
		
		EmpDTO data = empService.addDept(empId, empName, email, jobName, deptName);
		
		request.setAttribute("data", data);
		
		if(data != null) {
			if(data.getDeptId() != -1) {
				response.sendRedirect(request.getContextPath() + "/emps");
			} else {
				Map<String, String> error = new HashMap<String, String>();
				
				if(data.getDeptId() == -1) {
					error.put("deptId", "동일한 부서 ID가 존재합니다.");
				}
				request.setAttribute("error", error);
				request.getRequestDispatcher(view).forward(request, response);
			}
		} else {
			RequestDispatcher rd = null;			
			rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
		*/

		
	}

}
