<%@page import="locs.model.LocsDTO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>location 테이블 조회</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/test.css">	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<h1>locations 조회</h1>
	<section = class="container">
		<div>
			<form action="./locs" method="get">
				<div>
					<button class="btn btn-outline" type="button" onclick="location.href='./locs/add'">추가</button>
				</div>
				<div class="input-form form-right">
							<input class="input-text" type="text" name="search" data-required="지역코드를 입력하세요.">
							<button class="btn btn-outline" type="submit">조회</button>
				</div>
			</form>
		</div>	
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-auto">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>LOCATION_ID</th>
					<th>STREET_ADDRESS</th>
					<th>POSTAL_CODE</th>
					<th>CITY</th>
					<th>STATE_PROVINCE</th>
					<th>COUNTRY_ID</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
			<%
				if(request.getAttribute("locsDatas") != null) {
					List<LocsDTO> datas = (List<LocsDTO>) request.getAttribute("locsDatas");
					for(LocsDTO data: datas) {				
			%>		
				<tr>
					<td><%=data.getLocId() %></td>
					<td><%=data.getStreetAd() %></td>
					<td><%=data.getPosCode() %></td>
					<td><%=data.getCity() %></td>
					<td><%=data.getStaPro() %></td>
					<td><%=data.getCtrId() %></td>		
					<td>
						<button type="button" onclick="location.href='./locs/mod?id=<%=data.getLocId() %>'">
							<span class="material-symbols-outlined">edit</span>
						</button>
						<button type="button" class="btn btn-icon" onclick="location.href='./locs/del?id=<%=data.getLocId() %>'">
							<span class="material-symbols-outlined">delete</span>
						</button>
					</td>		
				</tr>
			<%
					}
				}
			%>
			</tbody>
		</table>
		
		<div id="nav">
			<ul>
				<li><a href="">Prev</a></li>
				<%
					if(request.getAttribute("pageList") != null) {
						List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
						for(Integer n : pageList) {
				%>
					<li><a href="./locs?page=<%=n%>"><%=n %></a></li>
				<%						
						}
					}
				%>
				
				<li><a href="">Next</a></li>
			</ul>
		</div>
	
	</section>
</body>
</html>