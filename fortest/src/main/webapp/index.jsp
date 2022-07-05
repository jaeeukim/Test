<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
<!-- msg값을 사용하지 않았다. -->
	<%=request.getAttribute("msg")%>
<!-- -------------------------------------------------- -->
<!-- list값을 setAttribute해주지 않아서 값을 받을 수 없는 상태 -->
<!-- NoticeListServlet에 request.setAttribute("list", list);작성한다. -->
	<!-- 
	<script>
		function logout() {
			location.href="<%=request.getContextPath() %>/logout";
		}
	</script>
	
	<% 
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	%>
	 -->
	
	
	
	<form action="<%= request.getContextPath() %>/binsert" method="post">
		<table align="center">
			<tr>
				<td>제목</td>	
				<td><input type="text" name="boardTitle"></td>
			</tr>	
			<tr>
				<td>작성자</td>	
				<td><input type="text" name="boardWriter"></td>
			</tr>	
			<tr>
				<td>내용</td>	
				<td><textarea cols="50" rows="7" name="boardContent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="등록하기">  
				<a href="/board/boardList">목록으로</a></td>
			</tr>
		</table>
	</form>
</body>
</html>