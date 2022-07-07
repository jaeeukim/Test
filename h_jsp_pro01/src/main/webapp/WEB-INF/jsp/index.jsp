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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath} /static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<!--  < %= request.getContextPath() %>를 $ {pageContext.request.contextPath}로 변경한것 -->
</head>
<body>
	<%
		String test = "Hello";	
	%>
	<%-- <jsp:incliude page="./module/navigation.jsp" %> 아래 식과 동일함 (jsp:include 액션 태그)--%>
	<%@ include file="./module/navigation.jsp" %> <!-- inlcude 지시자 -->
	<!-- 액션태그 : 실행된 결과 코드를 삽입 (동적페이지에 사용) -분산효과
	     지시자   : 코드를 삽입한 후에 실행 (정적페이지에 사용) -->



	<h1>Welcome JSP/Servlet</h1>	
	<%-- <c:xxx> 제어문 
		 <fmt:xxx> 포멧 (날짜, 숫자)
		 ${fn:xxx } 함수(문자열)
	--%>
	<c:if test="조건식(EL 사용)">
		
	</c:if>
	<c:choose>
		<c:when test="${param.x == 'a'}"></c:when>
			파라미터 x의 값이 a면 실행 
		<c:when test="${param.x == 'b' }"></c:when>
			파라미터 x의 값이 b면 실행 
		<c:when test="${param.x == 'c' }">
			파라미터 x의 값이 c면 실행 
		</c:when>
		<c:otherwise>
			모든 when 조건에 해당하지 않으면 실행됨
		</c:otherwise>	
	</c:choose>
	
	<hr>
	
	<%
		List<String> lst = new ArrayList<String>();
		lst.add("a"); lst.add("b"); lst.add("c"); lst.add("d");
		request.setAttribute("lst", lst);
	%>
	
	<ul>
		<c:forEach begin="1" end="5" var="v">
			<li>${v}</li>
		</c:forEach>
	</ul>
	<br>
	<ul>
		<c:forEach items="${lst} var="v">
			<li>${v}</li>
		</c:forEach>
	</ul>
</body>
</html>