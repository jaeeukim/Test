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
	<title>부서 수정</title>
	<%@ include file="../module/head.jsp" %>	
	
</head>
<body>
	<section class="container">
		<form action="./mod" method="post">
			<input type="hidden" name="deptId" value="${data.deptId }" readonly>
			<div class="input-form wide">
				<label class="input-label">부서명</label>				
				<input class="input-text" type="text" name="deptName" value="${data.deptName }" data-required="부서명을 입력하세요.">
			</div>
			<div class="input-form wide">
				<label class="input-label">관리자ID</label>			
				<input class="input-text" type="text" name="mngId" value="${data.mngId }" data-required="관리자ID를 입력하세요.">
				<c:if test="${errorCode == 'mngId'}">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역ID</label>			
				<input class="input-text" type="text" name="locId" value="${data.locId }" data-required="지역ID를 입력하세요.">
				<c:if test="${errorCode == 'locId' }">
					<label class="input-label-error">${errorMsg }</label>
				</c:if>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='../depts'">취소</button>
			</div>
		
		</form>
		
	</section>
	
	
</body>
</html>