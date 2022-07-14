<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보</title>
	<%@ include file = "../module/head.jsp" %>
</head>
<script type="text/javascript">
	window.onload = function() {
		var form = document.forms[0];
		form.email.addEventListener("input", enableSaveButton);
		form.phone.addEventListener("input", enableSaveButton);
		
		prevImage.addEventListener("click", function(e) {
			btnImage.click();
		});
		
		btnImage.addEventListener("change", showPreview);
	}
	
	function showPreview(e) {
		var file = e.target.files[0]; 		// 선택한 이미지의 파일 객체 정보가 저장되는 곳 (파일이 여러개일 수 있으니까 배열로 받아서 한개 가져오기)
		var imgUrl = URL.createObjectURL(file);		// 파일에 대한 URL 정보를 가져옴 
		prevImage.src = imgUrl;						// 해당 이미지의 경로 정보를 prevImage.src에 저장시킴
	}
	
	function enableSaveButton(e) {
		var submit = document.querySelector("button[type='submit']");
		var enable = submit.getAttribute("class").replace("disable", "");
		submit.setAttribute("class", enable);
	}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %> 
	<section class="container">
		<c:url var="updateUrl" value="/myInfo"/> <!-- contextPath가 자동으로 들어가는 c:url을 사용하자 -->
		<form class="large-form" action="${updateUrl }" method="post" enctype="multipart/form-data"> <!-- enctype="multipart/form-data" 업로드를 하기 위해 사용 -->
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<img id="prevImage" class="img-360" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
				<br>
				<input type="file" id="btnImage" name="uploadImage">
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto"  type="text" name="empId" value="${sessionScope.loginData.empId }" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto"  type="text" name="empName" value="${sessionScope.loginData.empName }" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" disabled>
						<c:forEach items="${jobDatas}" var="job">
							<c:choose>
								<c:when test = "${job.jobId == seesionScope.loginData.jobId}">
									<option value="${job.jobId}" selected>${job.jobName}</option>
								</c:when>
								<c:otherwise>
									<option value="${job.jobId}">${job.jobName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" disabled>
						<c:forEach items="${deptDatas}" var="dept">
							<c:choose>
								<c:when test = "${dept.deptId == seesionScope.loginData.deptId}">
									<option value="${dept.deptId}" selected>${dept.deptName}</option>
								</c:when>
								<c:otherwise>
									<option value="${dept.deptId}">${dept.deptName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto"  type="text" name="email" value="${sessionScope.loginData.email}">	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="fmtHireDate" value="${empDetail.hireDate}" dateStyle="long"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${fmtHireDate }" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${empDetail.phone }">	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatNumber var="fmtSalary" value="${empDetail.salary }"/>
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto"  type="text" name="salary" value="${fmtSalary}" disabled>
				</div>
				<div class="input-form">
					<fmt:formatNumber var="fmtCommission" value="${empDetail.commission }" type="percent"/>
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto"  type="text" name="commission" value="${fmtCommission }" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok disable" type="submit">저장</button>
			</div>
		</form>
	
	
	</section>
</body>
</html>