<%@page import="locs.model.LocsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 실행</title>
</head>
<body>
	<%
		LocsDTO data = (LocsDTO)request.getAttribute("data");
	%>
	<ul>
		<li>지역 ID : <%=data.getLocId() %></li>
		<li>주소 : <%=data.getStreetAd() %></li>
	</ul>
	<p>삭제할 데이터가 맞습니까?</p>
	
	<div>
		<button type="submit" form="deleteForm">삭제</button>
		<button type="button" onclick="history.back();">취소</button>
	</div>
	
	
	<form id="deleteForm" action="./del" method="post">
	
		<input type="hidden" name="locId" value="<%=data.getLocId() %>">
	
	</form>

</body>
</html>