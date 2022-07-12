<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file = "../module/head.jsp" %>
</head>
<body>
	<%@ include file="../module/navigation.jsp" %> 
	<section class="container">
		<form class="large-form" action="">
			<div class="img-form left">
				<c:url var="imgUrl" value="/static/img/emp/${sessionScope.loginData.empId}.png" />
				<img class="img-360" alt="여기에 증명사진이 배치됩니다." src="${imgUrl}" >
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto"  type="text" name="empId" value="${sessionScope.loginData.empId }" readonly>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto"  type="text" name="empName" value="${sessionScope.loginData.empName }">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId">
						<option></option>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId">
						<option></option>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto"  type="text" name="email" value="${sessionScope.logingData.email}">	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="">
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="">	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto"  type="text" name="salary" value="">
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto"  type="text" name="commission" value="">
				</div>
			</div>
		</form>
	
	
	</section>
</body>
</html>