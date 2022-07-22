<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>        
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 상세</title>
	<!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
	<%@ include file="../module/head.jsp" %>	

</head>

<body>
	<%@ include file="../module/navigation.jsp" %> 
	<section class="container">
		<div class="large-form"> <!-- css적용하려고 걍 만들어둠 -->
			<div class="img-form left">
				<c:url var="imgUrl" value="${imagePath}" />
				<input type="file" id="btnImage" name="uploadImage" 
				   onchange="showPreview(this, 'prevImage');" style="display: none;" disabled>
				<img id="prevImage" class="img-360"
				   onclick="btnImage.click();" alt="여기에는 증명 사진이 배치됩니다." src="${imgUrl}">
				<br>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<c:url var="ajaxDuplicateUrl" value="/ajax/duplicate"/>
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId"
					 value="${data.empId }" data-required="ID는 필수입력입니다."disabled >
					<label class="input-label-error"></label>	
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto"  type="text" name="empName" 
					value="${data.empName }" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" >
						<option value="${data.jobId}">${data.jobName}</option>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" >
						<option value="${data.deptId}">[${data.deptId}] ${data.deptName}</option>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto"  type="text" name="email"
					 value="${data.email }"disabled>	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="hireDate" value="${dataDetail.hireDate }" pattern="YYYY-MM-dd"/>
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" 
					value="${hireDate }" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone"
					 value="${dataDetail.phone }"disabled>	
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto"  type="number" name="salary" 
					value="${dataDetail.salary }" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto"  type="number" name="commission"
					 value="${dataDetail.commission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<c:url var="empModUrl" value="/emps/modify">
					<c:param name="id" value="${data.empId }"/>
				</c:url>
				<c:url var="empDelUrl" value="/emps/delete">
					<c:param name="id" value="${data.empId }"/>
				</c:url>
				<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${empModUrl}'">수정</button>
				<button class="btn btn-outline btn-cancel" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
			</div>
		</div>
	</section>
	<div class="modal" tabindex="-1" id="deleteModal">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" >직원삭제</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>해당 직원의 정보를 삭제하시겠습니까?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="empDelete(${data.empId});">삭제</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="modal" tabindex="-1" id="resultModal">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" >확인</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>삭제되었습니다.</p>
	      </div>
	      <div class="modal-footer">
	         <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='/jsp01/emps'">확인</button>
	      </div>
	    </div>
	  </div>
	</div>



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
<script type="text/javascript">
	function empDelete(empId) {
		$.ajax({
			type: "post",
			url: "/jsp01/ajax/delete",
			data: {
				id: empId,
				type: "emp"
			},
			dataType: "json",
			success: function(data) {
				var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
					keyboard: false					
				});
				var title = myModal._element.querySelector(".modal-title");
				var body = myModal._element.querySelector(".modal-body");
				title.innerText = data.title;
				body.innerHTML = "<p>" + data.message + "</p>";
					
				myModal.show();
			}
		})
	}

</script>



</html>