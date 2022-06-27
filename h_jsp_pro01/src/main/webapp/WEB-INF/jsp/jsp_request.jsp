<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - request 객체</title>
</head>
<body>
	<h1>JSP - request 객체</h1>
	<h2>getMethod()</h2>
	<p>
		request.getMethod() : <%=request.getMethod() %>
	</p>
	<form action="./jsp_request" method="get">
		<button type="submit">GET 전송</button>
	</form>
	<form action="./jsp_request" method="post">
		<button type="submit">POST 전송</button>
	</form>
	<hr>
	<h2>getParameter(name)</h2>
	<p>
		request.getParameter(name) : <%=request.getParameter("param_name") %><br>
		name은 input 태그 등에 사용하는 name 속성의 값을 지칭한다.
	</p>
	<form action="./jsp_request" method="get">
		<div>
			<input type="text" name="param_name">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
	<h2>getParameterValues(name)</h2>
	<p>
		request.getParameterValues(name) : 
		<%
			if(request.getParameterValues("param_chk") != null) {
		%>
			<%=Arrays.asList(request.getParameterValues("param_chk")) %>
		<%
			}
		%>
	
	</p>
	<form action="./jsp_request" method="get">
		<div>
			<input type="checkbox" value="a" name="param_chk">
			<input type="checkbox" value="b" name="param_chk">
			<input type="checkbox" value="c" name="param_chk">
			<input type="checkbox" value="d" name="param_chk">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<h2>getParameterNames()</h2>
	<p>
		<%
			Iterator<String> iter = request.getParameterNames().asIterator();
			while(iter.hasNext()) {
		%>
			<%=iter.next() %>
		<%
			}
		%>
		<br>
		input 태그 등에 사용하는 name 속성의 값을 지칭한다.
	</p>
	<form action="./jsp_request" method="get">
		<div> 
			<input type="text" name="username">
			<input type="text" name="password">
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
	<hr>
</body>
</html>