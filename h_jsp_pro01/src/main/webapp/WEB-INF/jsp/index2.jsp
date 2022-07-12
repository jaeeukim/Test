<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- jstl의 tag기능을 사용하기 위해서 taglib를 등록했다. 
     가독성과 코드량에서 이득을 볼 수 있다.-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>	
	<%@ include file = "./module/head.jsp" %>
</head>
<body>
	<%@ include file="./module/navigation.jsp" %> 
	<session class="container">
		${sessionScope.loginData.empName} 님 환영합니다.
	</session>
</body>
</html>