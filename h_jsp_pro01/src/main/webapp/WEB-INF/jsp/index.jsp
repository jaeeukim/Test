<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- jstl을 사용하기 위해서 taglib를 등록했다. 
     가독성과 코드량에서 이득을 볼 수 있다.-->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath} /static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<!--  < %= request.getContextPath() %>를 $ {pageContext.request.contextPath}로 변경한것 -->
</head>
<body>
	<%
		String test = "Hello";	
	%>
	<!-- <jsp:incliude page="./module/navigation.jsp" %> 아래 식과 동일함 (jsp:include 액션 태그)-->
	<%@ include file="./module/navigation.jsp" %> <!-- inlcude 지사자 -->
	
	<!-- 액션태그 : 실행된 결과 코드를 삽입 (동적페이지에 사용) -분산효과
	     지사자   : 코드를 삽입한 후에 실행 (정적페이지에 사용) -->
	
	
	<h1>Welcome JSP/Servlet</h1>	
</body>
</html>