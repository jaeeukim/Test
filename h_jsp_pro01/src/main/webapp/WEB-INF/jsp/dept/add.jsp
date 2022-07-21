<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dept.model.DeptDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>	
</head>
<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate" />
<c:url var="ajaxExistsUrl" value="/ajax/exists" />
<body>
	<!--  검색 조회는 get (db에 변경작업X), 추가 수정은 post(db에 변경작업) -->
 	<%@ include file="../module/navigation.jsp" %>
 	<%--
	<section class="container">
		<div>
			<h2>EL확인용 (parameter 데이터 값)</h2>
			<ul>
				<li>${param.deptId}</li> <!-- jstl사용으로 request.getParameter("deptID")를 간단하게 사용가능하다 -->
				<li>${param.deptName}</li>
				<li>${param.mngId}</li>
				<li>${param.locId}</li>
				<li>${param.name}</li> <!-- 존재하지않는 변수는 그냥 비워둔다. -->
			</ul>
		</div>
		<div>
			<h2>EL request에 설정된 속성 사용 (request 객체에 setAttribute했던 값 가져오기)</h2>
			<ul>
				<li>${requestScope.data.deptId }</li> <!-- 에러나면 -1 (우리가 return -1로 설정했었음) -->
				<li>${requestScope.data.deptName }</li> <!-- requestScope는 생략이 가능하다!!!! -->
				<li>${requestScope.data.mngId }</li>
				<li>${requestScope.data.locId }</li>
			</ul>
		</div>
		</section>
		<%
			Map<String, String> error = request.getAttribute("error") != null ? (Map<String, String>)request.getAttribute("error") : new HashMap<String, String>();
			// DeptDTO data = request.getAttribute("data") != null ? (DeptDTO)request.getAttribute("data") : null; 생략가능
		%>
 	 --%>
 	 
 	 
 	 
		<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<input type="text" class="input-text" name="deptId" onblur="duplicateCheck(this, '${ajaxDuplicateUrl}');"
				       value="${data.deptId  == -1 ? '' : data.deptId}" data-required="부서 ID를 입력하세요.">  
				       <%--  함수내에 this.value나 this로하면 document.forms[0].어쩌구로 안하고 간단하게 가능하다  --%>
															<%-- < %=data == null ? "" : data.getDeptId() %> 대신 ${}사용 --%>
				<%--  	<input type="text" class="input-text" name="deptId" value="${param.deptId}" data-required="부서 ID를 입력하세요."> --%>
															<%--< %=data.getLocId() == -1 ? "" : data.getDeptId() %> 대신 ${}사용 --%>
				<label class="input-label-error"></label>
					<%-- < % if(error.get("deptId") != null) {  // 또는 ${not empty error.deptId }
						<label class="input-label-error">${error.deptId }</label>
													<!--  < %=error.get("deptId") %>대신 ${}사용 -->
					<% } %> --%>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
					<input type="text" class="input-text" name="deptName" value="${data.deptName }" data-required="부서명을 입력하세요.">
					<label class="input-label-error"></label>
					
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>
				<input type="text" class="input-text" name="mngId"  onblur="existsCheck(this, '${ajaxExistsUrl}');"
					   value="${data.mngId == -1 ? '' : data.mngId}" data-required="관리자 ID를 입력하세요.">
				<label class="input-label-error"></label>
				
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input type="text" class="input-text" name="locId" onblur="existsCheck(this, '${ajaxExistsUrl}');"
					   value="${data.locId == -1 ? '' : data.locId}" data-required="지역 ID를 입력하세요.">
				<label class="input-label-error"></label>
				
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>
		<%-- <h1>부서 추가</h1>
		< %
			String deptId = "";
			String deptName = "";
			String mngId = "";
			String locId = "";
			if(request.getAttribute("error") != null) {
				DeptDTO dto = (DeptDTO)request.getAttribute("error");
				deptId = dto.getDeptId() == -1 ? "" : String.valueOf(dto.getDeptId());
				deptName = dto.getDeptName();
				mngId = dto.getMngId() == -1 ? "" : String.valueOf(dto.getMngId());
				locId = dto.getLocId() == -1 ? "" : String.valueOf(dto.getLocId());
		%>
			<script type="text/javascript">
				alert("< %=request.getAttribute("errorMsg") %>");
			</script>
		
		< %
			}	
		%>
		
		<form action="./add" method="post">
			<div>
				<input type="text" name="deptId" value="< %=deptId %>" placeholder="부서 ID">
			</div>
			<div>
				<input type="text" name="deptName" value="< %=deptName %>" placeholder="부서 이름">
			</div>
			<div>
				<input type="text" name="mngId" value="< %=mngId %>" placeholder="관리자 ID">
			</div>
			<div>
				<input type="text" name="locId" value="< %=locId %>" placeholder="지역 ID">
			</div>
			<button type="submit">저장</button>
		
		</form>
		--%>
	
	</section>
	
</body>
</html>