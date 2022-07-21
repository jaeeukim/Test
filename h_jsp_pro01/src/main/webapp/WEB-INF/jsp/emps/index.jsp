<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 조회</title>
	<%@ include file="../module/head.jsp" %> <!-- 온갖 css와 jsp연결을 모은곳 -->
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./emps" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" name="addbuttons" onclick="location.href='./emps/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search" data-required="부서코드를 입력하세요.">
					<button class="btn btn-outline" type="submit">조회</button>
					<!-- div class="required-box show">부서코드를 입력하세요.</div -->
					<select class="select-form" onchange="location.href='./emps?pageCount=' + this.value">
						<option value="5" ${sessionScope.pageCount == 5 ? 'selected' : ''} >5 개</option>
						<option value="10" ${sessionScope.pageCount == 10 ? 'selected' : '' } >10 개</option>
						<option value="15" ${sessionScope.pageCount == 15 ? 'selected' : ''} >15 개</option>
						<option value="20" ${sessionScope.pageCount == 20 ? 'selected' : '' }>20 개</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>직원ID</th>
					<th>직원이름</th>
					<th>이메일</th>
					<th>직급</th>
					<th>부서</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas }">
					<c:url var="detailUrl" value="/emps/detail"/>
					<c:forEach items="${datas }" var = "data"> <%-- var = 반복문 안에서 사용될 el문의 이름 --%>
						<%--
							<c:url var="detailUrl" value="/emps/detail">
								<c:param name="id" value="${data.empId}"/>
							</c:url>  로도 사용 가능하다.							
						 --%>
					
					
						<tr onclick="location.href='${detailUrl}?id=${data.empId}'">
							<td>${data.empId }</td>
							<td>${data.empName }</td>
							<td>${data.email }</td>
							<td>${data.jobName }</td>
							<td>${data.deptName }</td>
							<%-- 수정 및 삭제는 페이지 상세내역안에 넣을 예정
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./emps/mod">
									<c:param name="id" value="${data.empId }"/>
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./emps/del">
									<c:param name="id" value="${data.empId }" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							</td>
							 --%>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${not empty pageList}">
				<c:url var="pageUrl" value="./emps" />
				<%@ include file="../module/paging.jsp" %>
			</c:when>
			<c:otherwise>
				<div class="input-form wide form-left">
					<button class="btn btn-outline btn-ok" type="button" onclick="location.href='<%=request.getContextPath() %>/emps'">전체보기</button>
				</div>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>