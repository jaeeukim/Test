<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String url = "";
	if(request.getAttribute("url") != null) {
		url = (String)request.getAttribute("url");
	}
%>
<header>
	<nav class="top-nav">
		<ul class="nav">
					<!-- url에 jsp_가 지정되면(해당 페이지를 보고있으면) 색상을 부여하기 위해 사용 -->
			<li class="nav-item dropdown<%=url.contains("/jsp_") ? " active" : "" %>">
				<a class="nav-link" href="/">JSP/Servlet</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<a class="nav-link" href="./jsp_script">스크립트 태그</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_request">request 객체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_response">response 객체</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp_model2">Model2</a>
					</li>
				</ul>
			</li>
			<c:if test="${not empty sessionScope.loginData }">
				<li class="nav-item<%=url.contains("/emps") ? " active" : "" %>">
					<a class="nav-link" href="../emps">직원</a>
				</li>
				<li class="nav-item<%=url.contains("/depts") ? " active" : "" %>">
					<a class="nav-link" href="../depts">부서</a>
				</li>
				<li class="nav-item<%=url.contains("/locs") ? " active" : "" %>">
					<a class="nav-link" href="../locs">지역</a>
				</li>
			</c:if>
		</ul>
		<c:if test="${not empty sessionScope.loginData }">
			<ul class="nav right">
				<li class="nav-item dropdown">
					<a class="nav-link" href="#">${sessionScope.loginData.empName }</a>
					<ul class="nav dropdown-nav">
						<c:url var="myInfoUrl" value="/myInfo" />				
						<li class="nav-item">
							<a class="nav-link" href="${myInfoUrl }">개인정보</a>
						</li>					
						<c:url var="logoutUrl" value="/logout" />				
						<li class="nav-item">
							<a class="nav-link" href="${logoutUrl }">로그아웃</a>
						</li>					
					</ul>
				</li>
			
			
			
			<%--  이런식으로 어딘가에 선언된 변수를 사용하면 선언되지 않은곳에서는 오류가 발생할수 있기 때문에
			      만약 가져와서 쓰고 싶다면 getAttribute를 응용하는걸로 하자
				 <li class="nav-item<%=url.contains("/locs") ? " active" : "" %>"> 
					<a class="nav-link" href="./locs"> <%=test %></a>
				</li>
			--%>	
		</ul>
	</c:if> 
	</nav>
</header>