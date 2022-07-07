<%@page import="locs.model.LocsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>추가페이지</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/form.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
	
</head>
<body>
	<%
		String locId = "";
		String streetAd ="";
		String posCode ="";
		String city ="";
		String staPro ="";
		String ctrId ="";
	
		if(request.getAttribute("error") != null) {
			LocsDTO dto = (LocsDTO)request.getAttribute("error");
			locId = dto.getLocId() == -1 ? "" : String.valueOf(dto.getLocId());
			streetAd = dto.getStreetAd();
			posCode = dto.getPosCode();
			city = dto.getCity();
			staPro = dto.getStaPro();
			ctrId = dto.getCtrId() == null ? "" : String.valueOf(dto.getCtrId());
	%>
		<script type="text/javascript">
			alert("<%=request.getAttribute("errorMsg") %>");
		</script>
	<%
		}
	%>
	<form action="./add" method="post">
		<div>
			<input type="text" name="locId" value="<%=locId %>" placeholder="지역ID">
		</div> 
		<div>
			<input type="text" name="streetAd" value="<%=streetAd %>"  placeholder="주소">
		</div> 
		<div>
			<input type="text" name="posCode"  value="<%=posCode %>" placeholder="우편번호">
		</div> 
		<div>
			<input type="text" name="city" value="<%=city %>" placeholder="도시" required>
		</div> 
		<div>
			<input type="text" name="staPro" value="<%=staPro %>" placeholder="지역">
		</div> 
		<div>
			<input type="text" name="ctrId" placeholder="나라ID">
		</div> 
		<div>
			<button type="submit">추가</button>
		</div>
	
	
	</form>
</body>
</html>