<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>        
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>게시판 수정 - ${data.title}</title>
	<link rel="stylesheet" type="text/css" href="/jsp01/static/bs5/css/bootstrap.min.css">
	<script type="text/javascript" src="/jsp01/static/bs5/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function formCheck(form) 	{
		if(form.title.value === undefined || form.title.value.trim() === "") {
			// 모달 활성
			var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
				keyboard: false
			})
			modal.show();
			return;
		}
		form.submit();
	}
</script>

<body>
	<header></header>
	<section class="container"> 
		<div class="mt-3">
			<c:url var="boardModifuUrl" value="/board/modify"/>
			<form action="${boardModifuUrl}" method="post">
				<input type="hidden" name="id" value="${data.id}"> <!-- paramter전달하려고 사용하는 pk값 -->
				<div class="mb-3">
					<input class="form-control" id="id_title" name="title" placeholder="제목을 입력하세요." value="${data.title }">
				</div>
				<div class="mb-3">
					<textarea class="form-control" id="id_content" name="content"
					      rows="5" placeholder="내용을 입력하세요.">${data.content }</textarea>
				</div>
				<div class="text-end">
					<button type="button" class="btn btn-primary" onclick="formCheck(this.form);">저장</button>
				</div>
			</form>
		</div>
		<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel">
		  	<div class="modal-dialog">
			    <div class="modal-content">
				      <div class="modal-header">
					        <h5 class="modal-title" id="errorModalLabel">오류</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				      		<c:choose>
				      			<c:when test="${empty errorMsg }">
						       	 	제목은 공란이 올 수 없습니다. 반드시 제목을 입력하세요.
								</c:when>				      			
				      		</c:choose>
				      </div>
				      <div class="modal-footer">
					        <button type="button" class="btn btn-danger btn-sm" data-bs-dismiss="modal">확인</button>
				      </div>
				    </div>
			  </div>
		</div>
	</section>
	<footer></footer>
	<c:if test="${not empty errorMsg}">
		<script type="text/javascript">
			var modal = 
		</script>
	</c:if>
</body>
</html>
