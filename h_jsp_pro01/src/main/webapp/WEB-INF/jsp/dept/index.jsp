<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
</head>
<script type="text/javascript">
window.onload = function() {
	var form = document.forms[0];
	form.addEventListener("submit", formCheck);
}

function formCheck(e) {
	if(this.search.value === "") {
		e.preventDefault();
	} else {
		this.submit();
	}
}
</script>
<body>
	<h1>부서 조회 결과</h1>
	<form action="./depts" method="get">
		<div>
			<input type="text" name="search">
			<button type="submit">조회</button>
		</div>
	</form>
	
	
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
		</tr>
	<%
		if(request.getAttribute("deptDatas") != null) {
		 	List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas");
			for(DeptDTO data: datas) {
			
	%>
		<tr>
			<td><%=data.getDeptId() %></td>
			<td><%=data.getDeptName() %></td>
			<td><%=data.getMngId() %></td>
			<td><%=data.getLocId() %></td>
		</tr>
	
	<%
			}
		}
	%>
	
	</table>
</body>
</html>