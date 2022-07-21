<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>        
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 추가</title>
	<%@ include file="../module/head.jsp" %>	
</head>
<body>
	<%@ include file="../module/navigation.jsp" %> 
	<section class="container">
		<c:url var="empsAddUrl" value="/emps/add"/> <!-- contextPath가 자동으로 들어가는 c:url을 사용하자 -->
		<form class="large-form" action="${empsAddUrl}" method="post" enctype="multipart/form-data"> <!-- enctype="multipart/form-data" 업로드를 하기 위해 사용 -->
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<input type="file" id="btnImage" name="uploadImage" 
				   onchange="showPreview(this, 'prevImage');" style="display: none;">
				<img id="prevImage" class="img-360"
				   onclick="btnImage.click();" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
				<br>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate"/>
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" 
						onblur="duplicateCheck(this, '${ajaxDuplicateUrl}')" value="" data-required="ID는 필수입력입니다.">
					<label class="input-label-error"></label>	
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto"  type="text" name="empName" value="" >
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" >
						<c:forEach items="${jobDatas}" var="job">
							<option value="${job.jobId}">${job.jobName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" >
						<c:forEach items="${deptDatas}" var="dept">
							<option value="${dept.deptId}">[${dept.deptId}] ${dept.deptName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto"  type="text" name="email" value="">	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="now" value="<%= new java.util.Date() %>" pattern="YYYY-MM-dd"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${now }" >
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="">	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto"  type="number" name="salary" value="0" >
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto"  type="number" name="commission" value="0" >
				</div>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok " type="submit">저장</button>
			</div>
		</form>
	</section>
	



	<%--
 	<%@ include file="../module/navigation2.jsp" %>
	<section class="container">
		<!-- <c:url var="empAdd" value="/emps/add/" /> -->
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input type="text" class="input-text">		
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">직원이름</label>
				<input type="text" class="input-text">		
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">이메일</label>	
				<input type="text" class="input-text">		
				<label class="input-label-error"></label>
			</div>
			<div class="input-form wide">
				<label class="input-label">직급</label>
				<select class="select-form">
					<c:forEach items="${empDatas}" var="empDto">
						<option value="${empDto.jobName }">
							${empDto.jobName}
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId" >
					<c:forEach items="${empDatas}" var="empDto">
						<option value="${empDto.deptName }">
							[${empDto.deptId}] ${empDto.deptName}
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">취소</button>
			</div>
		</form>	
	</section>
	 --%>
</body>
</html>