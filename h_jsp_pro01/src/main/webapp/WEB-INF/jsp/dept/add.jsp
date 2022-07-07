<%@page import="java.util.Map"%>
<%@page import="dept.model.DeptDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/form.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
	
</head>
<body>
 	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<h2>EL확인용</h2>
			<ul>
				<li>${param.deptId}</li> <!-- jstl사용으로 request.getParameter("deptID")를 간단하게 사용가능하다 -->
				<li>${param.deptName}</li>
				<li>${param.mngId}</li>
				<li>${param.locId}</li>
				<li>${param.name}</li> <!-- 존재하지않는 변수는 그냥 비워둔다. -->
			</ul>
		</div>
		<div>
			<h2>EL request에 설정된 속성 사용 (객체에 저장된 값 가져오기)</h2>
			<ul>
				<li>${requestScore.data.deptId }</li> <!-- 에러나면 -1 (우리가 return -1로 설정했었음) -->
				<li>${requestScore.data.deptName }</li>
				<li>${requestScore.data.mngId }</li>
				<li>${requestScore.data.locId }</li>
			</ul>
		</div>
		<%
			Map<String, String> error = request.getAttribute("error") != null ? (Map<String, String>)request.getAttribute("error") : null;
			DeptDTO data = request.getAttribute("data") != null ? (DeptDTO)request.getAttribute("data") : null;
		%>
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<% if(error == null) { %>
					<input type="text" class="input-text" name="deptId" value="${requestScore.data.deptId }" data-required="부서 ID를 입력하세요.">
															<!-- < %=data == null ? "" : data.getDeptId() %> 대신 ${}사용 -->
				<% } else { %>
					<input type="text" class="input-text" name="deptId" value="${param.deptId}" data-required="부서 ID를 입력하세요.">
															<!--< %=data.getLocId() == -1 ? "" : data.getDeptId() %> 대신 ${}사용 -->
					<% if(error.get("deptId") != null) { %>
						<label class="input-label-error"><%=error.get("deptId") %></label>
					<% } %>
				<% } %>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<% if(error == null) { %>
					<input type="text" class="input-text" name="deptName" value="${requestScore.data.deptName }" data-required="부서명을 입력하세요.">
				<% } else { %>
					<input type="text" class="input-text" name="deptName" value="${param.deptName}" data-required="부서명을 입력하세요.">
					<% if(error.get("deptName") != null) { %>
						<label class="input-label-error"><%=error.get("deptName") %></label>
					<% } %>
				<% } %>
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label> 
				<% if(error == null) { %>
					<input type="text" class="input-text" name="mngId" value="${requestScore.data.mngId }" data-required="관리자 ID를 입력하세요.">
				<% } else { %>
					<input type="text" class="input-text" name="mngId" value="${param.mngId}" data-required="관리자 ID를 입력하세요.">
					<% if(error.get("mngId") != null) { %>
						<label class="input-label-error"><%=error.get("mngId") %></label>
					<% } %>
				<% } %>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<% if(error == null) { %>
					<input type="text" class="input-text" name="locId" value="${requestScore.data.locId }" data-required="지역 ID를 입력하세요.">
				<% } else { %>
					<input type="text" class="input-text" name="locId" value="${param.locId}" data-required="지역 ID를 입력하세요.">
					<% if(error.get("locId") != null) { %>
						<label class="input-label-error"><%=error.get("locId") %></label>
					<% } %>
				<% } %>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>
		<!-- <h1>부서 추가</h1>
		< %
			String deptId = "";
			String deptName = "";
			String mngId = "";
			String locId = "";
			if(request.getAttribute("error") != null) {
				DeptDTO dto = (DeptDTO)request.getAttribute("error");
				deptId = dto.getDeptId() == -1 ? "" : String.valueOf(dto.getDeptId());
				deptName = dto.getDeptName();
				mngId = dto.getMngId() == -1 ? "" : String.valueOf(dto.getMngId());
				locId = dto.getLocId() == -1 ? "" : String.valueOf(dto.getLocId());
		%>
			<script type="text/javascript">
				alert("< %=request.getAttribute("errorMsg") %>");
			</script>
		
		< %
			}	
		%>
		
		<form action="./add" method="post">
			<div>
				<input type="text" name="deptId" value="< %=deptId %>" placeholder="부서 ID">
			</div>
			<div>
				<input type="text" name="deptName" value="< %=deptName %>" placeholder="부서 이름">
			</div>
			<div>
				<input type="text" name="mngId" value="< %=mngId %>" placeholder="관리자 ID">
			</div>
			<div>
				<input type="text" name="locId" value="< %=locId %>" placeholder="지역 ID">
			</div>
			<button type="submit">저장</button>
		
		</form>
		-->
	
	</section>
	
</body>
</html>