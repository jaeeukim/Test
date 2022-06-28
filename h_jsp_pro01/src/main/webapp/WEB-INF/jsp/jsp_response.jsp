<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - Response 객체</title>
</head>
<body>
	<h1>JSP - Response 객체</h1>
	<h2>sendRedirect(String url)</h2>
	<%
		// response.sendRedirect("./");
		// f12 network에서 redirect실행을 확인 가능하다
	%>
	<p>
		클라이언트에게 다른 주소로 재요청을 하게 만들기 위해 사용하는 기능
		HTTP 상태 코드 302
	</p>
	<hr>
	<h2>sendStatus(int statusCode)</h2>
	<%
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		// sendStatus만 하면 status만 바뀌고 실행은 잘됨.
		// 직접 숫자를 입력하기보다는 HttpServletResponse.으로
		// 검색해서 선택해준다. http상태코드를 검색해보면 500, 404, 400, 401...
	%>
	<!-- 2xx : 정상 / 3xx : 리다이렉트 / 4xx : 요청오류 / 5xx : 서버오류 -->
	<p>
	</p>
	<hr>
	<h2>sendError(int statusCode)</h2>
	<%
		// response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    // 메시지 전달가능
		// response.sendError(HttpServletResponse.SC_NOT_FOUND, "잘못된 url주소입니다.");
	%>
	<p></p>
	<hr>
	<h2>setContentType(String mimeType)</h2>
	<p></p>
	<%
		// response.setContentType("text/javascript");
		// response.setContentType("text/html");
	%>
	<hr>
</body>
</html>