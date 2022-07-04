<%@page import="locs.model.LocsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>수정 페이지</title>
</head>
<body>
	<%
		if(request.getAttribute("errorMsg") != null) {		
	%>
		<script type="text/javascript">
			alert("<%=request.getAttribute("errorMsg") %>");
		</script>

	<%
		}
	%>
	
	<% 
		LocsDTO data = (LocsDTO)request.getAttribute("data");
	%>
	<form action="./mod" method="post">
		<div>
			<input type="hidden" name="locId" value="<%=data.getLocId() %>" readonly>		
		</div> 
		<div>
			<input type="text" name="streetAd" value="<%=data.getStreetAd() %>" placeholder="주소">
		</div> 
		<div>
			<input type="text" name="posCode" value="<%=data.getPosCode() %>"placeholder="우편번호">
		</div> 
		<div>
			<input type="text" name="city" value="<%=data.getCity()%>"placeholder="도시" required>
		</div> 
		<div>
			<input type="text" name="staPro" value="<%=data.getStaPro() %>"placeholder="지역">
		</div> 
		<div>
			<input type="text" name="ctrId" value="<%=data.getCtrId() %>"placeholder="나라ID">
		</div> 
		<div>
			<button type="submit">수정</button>
		</div>
	
	</form>

</body>
</html>