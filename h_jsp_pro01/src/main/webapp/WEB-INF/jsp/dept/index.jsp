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
	<div>
		<button type="button" onclick="location.href='./depts/add'">추가</button>
	</div>
	
	
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
			<th></th>
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
			<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td>
			<td>
				<button type="button" onclick="location.href='./depts/mod?id=<%=data.getDeptId()%>'">수정</button>
				<button type="button" onclick="location.href='./depts/del?id=<%=data.getDeptId()%>'">삭제</button>
			</td>
		</tr>
	
	<%
			}
		}
	%>
	</table>
	<div>
		<ul>
			<li><a href="">Prev</a></li>
			<%
				if(request.getAttribute("pageList") != null) {
					List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");	
					for(Integer n: pageList) {			
			%>
				<li><a href="./depts?page=<%=n %>"><%=n %></a></li>
			<%			
					}
				}
			%>
			<li><a href="">Next</a></li>
		</ul>
	</div>
</body>
</html>