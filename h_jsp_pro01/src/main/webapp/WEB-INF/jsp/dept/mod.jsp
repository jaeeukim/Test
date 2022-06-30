<%@page import="dept.model.DeptDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 수정</title>
</head>
<body>
	<h1>부서 수정</h1>
	<%
		if(request.getAttribute("errorMsg") != null) {		
	%>	
		<script type="text/javascript">
			alert("<%=request.getAttribute("errorMsg") %>");
		</script>
	<%
		}		
	%>
	
	
	
	<%
		DeptDTO data = (DeptDTO) request.getAttribute("data");
	%>
	<form action="./mod" method="post">
		<div>
			<input type="hidden" name="deptId" value="<%=data.getDeptId()%>" readonly>
		</div>
		<div>
			<input type="text" name="deptName" value="<%=data.getDeptName() %>" placeholder="부서 이름">
		</div>
		<div>
			<input type="text" name="mngId" value="<%=data.getMngId() %>" placeholder="관리자 ID">
		</div>
		<div>
			<input type="text" name="locId" value="<%=data.getLocId() %>" placeholder="지역 ID">
		</div>
		<button type="submit">저장</button>
	
	</form>
	
	
	
</body>
</html>