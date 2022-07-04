<%@page import="locs.model.LocsDTO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>location 테이블 조회</title>
	<style type="text/css">
		.required-box {
			margin: 0; padding: 0.3rem 0.6rem;
			box-sizing: border-box;
			display: inline;
			position: relative;
			border: 1px solid black;
			border-radius: 4px;
			background-color: black;
			color: white;
			box-shadow: 2px 2px 2px gray;
			opacity: 0;
			transition: opacity 0.5s;
		}
		.required-box.show {
			opacity: 1;
			transition: opacity 0.5s;
		}
		.required-box:after {
			content: '';
			position: absolute;
			top: 0; left: 15%;
			width: 0; height: 0;
			border: 6px solid transparent;
			/* border-bottom-color: white; */
			border-top: 0;
			margin-left: -6px; margin-top: -6px;
		}
		.required-box:before {
			content: '';
			position: absolute;
			top: 0; left: 15%;
			width: 0; height: 0;
			border: 7px solid transparent;
			border-bottom-color: black;
			border-top: 0;
			margin-left: -7px; margin-top: -7px;
		}
	</style>
</head>
<script type="text/javascript">
window.onload = function() {
	var form = document.forms[0];
	form.addEventListener("submit", formCheck);
}

function formCheck(e) {
	for(element of e.target.querySelectorAll("[data-required]")) {
		if(element.value === "") {
			e.preventDefault();
			if(!document.querySelector(".required-box")) {
				requiredBox(element, element.dataset.required);
			}
			return false;
		}
	}
	this.submit();
}

function requiredBox(element, message) {
	var box = document.createElement("div");
	box.setAttribute("class", "required-box");
	box.innerText = message;
	element.parentElement.append(box);
	
	box.style.left = element.offsetLeft - box.offsetLeft + (element.offsetWidth / 10) + "px";
	box.style.top = element.offsetHeight + 16 + "px";
	box.setAttribute("class", "required-box show");
	
	setTimeout(function() {
		box.remove();
	}, 1500);
}
</script>
<body>
	<h1>locations 조회</h1>
	<div>
		<button type="button" onclick="location.href='./locs/add'">추가</button>
	</div>
	<form action="./locs" method="get">
		<div>
			<input type="text" name="search" data-required="지역코드를 입력하세요.">
			<button type="submit">조회</button>
		</div>
	</form>
	
	<table>
		<tr>
			<th>LOCATION_ID</th>
			<th>STREET_ADDRESS</th>
			<th>POSTAL_CODE</th>
			<th>CITY</th>
			<th>STATE_PROVINCE</th>
			<th>COUNTRY_ID</th>
			<th></th>
		</tr>
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
					<button type="button" onclick="location.href='./locs/mod?id=<%=data.getLocId() %>'">수정</button>
					<button type="button" onclick="location.href='./locs/del?id=<%=data.getLocId() %>'">삭제</button>
				</td>		
			</tr>
		<%
				}
			}
		%>
	</table>
	
</body>
</html>