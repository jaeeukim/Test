<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<%@ include file="../module/head.jsp" %> 	
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./board" method="get">
				<div>
					<button class="btn btn-outline" type="button" onclick="location.href='./board/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search" data-required="게시글 번호를 입력하세요.">
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
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty boardDatas }">
				 	<c:forEach items="${boardDatas }" var="data">
			 			<c:url var="detailUrl" value="/board/detail">
							<c:param name="id" value="${data.id}" />
						</c:url>
				 		<tr onclick="location.href='${detailUrl}'">
							<td>${data.id}</td>
							<td>${data.title}</td>
							<td>${data.empId}</td>
							<td>
								<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long"/>			
								${createDate }
							</td>
							<td>${data.viewCnt}</td>
							<td>${data.like }</td>				
						</tr>
				 	</c:forEach>
				</c:if>
			</tbody>
		</table>

	</section>
</body>
</html>