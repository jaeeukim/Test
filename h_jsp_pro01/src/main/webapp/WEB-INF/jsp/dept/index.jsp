<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
	<%@ include file="../module/head.jsp" %> <!-- 온갖 css와 jsp연결을 모은곳 -->
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./depts" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./depts/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search" data-required="부서코드를 입력하세요.">
					<button class="btn btn-outline" type="submit">조회</button>
					<!-- div class="required-box show">부서코드를 입력하세요.</div -->
					<select class="select-form" onchange="location.href='./depts?pgc=' + this.value">
						<option value="5" ${pageCount == 5 ? 'selected' : ''} >5 개</option>
						<option value="10" ${pageCount == 10 ? 'selected' : '' } >10 개</option>
						<option value="15" ${pageCount == 15 ? 'selected' : ''} >15 개</option>
						<option value="20" ${pageCount == 20 ? 'selected' : '' }>20 개</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-60">
				<col class="col-auto">
				<col class="col-60">
				<col class="col-60">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>DeptId</th>
					<th>DeptName</th>
					<th>MngId</th>
					<th>LocId</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty deptDatas }">
					<c:forEach items="${deptDatas }" var = "data"> <%-- var = 반복문 안에서 사용될 el문의 이름 --%>
							<tr>
							<td>${data.deptId }</td>
							<td>${data.deptName }</td>
							<td>${data.mngId }</td>
							<td><a href="./locs?search=${data.locId }">${data.locId}</a></td>
							<td class="border-hidden-right">
								<c:url var="modUrl" value="./depts/mod">
									<c:param name="id" value="${data.deptId }"/>
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${modUrl}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<c:url var="delUrl" value="./depts/del">
									<c:param name="id" value="${data.deptId }" />
								</c:url>
								<button type="button" class="btn btn-icon" onclick="location.href='${delUrl}'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							
								<%-- jstl의 url을 사용하여 위에 정리함.
									<button type="button" class="btn btn-icon" onclick="location.href='./depts/mod?id=${data.deptId }'">
										<span class="material-symbols-outlined">edit</span>
									</button>
									<button type="button" class="btn btn-icon" onclick="location.href='./depts/del?id=${data.deptId }'">
										<span class="material-symbols-outlined">delete</span>
									</button>
								 --%>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			
			<%-- 위에 jstl 문으로 작성한 것과 동일한 문단
			<%
				if(request.getAttribute("deptDatas") != null) {
					List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas");
					for(DeptDTO data: datas) {
			%>
						<tr>
							<td><%=data.getDeptId() %></td>
							<td><%=data.getDeptName() %></td>
							<td><%=data.getMngId() %></td>
							<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td>
							<td class="border-hidden-right">
								<button type="button" class="btn btn-icon" onclick="location.href='./depts/mod?id=<%=data.getDeptId() %>'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<button type="button" class="btn btn-icon" onclick="location.href='./depts/del?id=<%=data.getDeptId() %>'">
									<span class="material-symbols-outlined">delete</span>
								</button>
							</td>
						</tr>
		<%
					}
				}
		%>
		--%>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${not empty pageList }">
				<c:url var="pageUrl" value="./depts" />			
				<%@ include file="../module/paging.jsp" %>
			</c:when>
			<c:otherwise>
				<div class="input-form wide form-left">
					<button class="btn btn-outline btn-ok" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">전체보기</button>
				</div>
			</c:otherwise>
		</c:choose>
		
		
		<%-- 위에 jstl문과 동일함 
			<% if(request.getAttribute("pageList") != null) { %>
				<%@ include file="../module/paging.jsp" %>
			<% } else { %>
				<div class="input-form wide form-left">
					<button class="btn btn-outline btn-ok" type="button" onclick="location.href='<%=request.getContextPath() %>/depts'">전체보기</button>
				</div>
			<% } %>
		 --%>
	</section>


	<%-- 기존 방식
	<div>
		<button type="button" onclick="location.href='./depts/add'">추가</button>
	</div>
	
	
	<form action="./depts" method="get">
		<div>
			<input type="text" name="search">
			<button type="submit">조회</button>
		</div>
	</form>
	
	
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
			<th></th>
		</tr>
	<%
		if(request.getAttribute("deptDatas") != null) {
		 	List<DeptDTO> datas = (List<DeptDTO>) request.getAttribute("deptDatas");
			for(DeptDTO data: datas) {
			
	%>
		<tr>
			<td><%=data.getDeptId() %></td>
			<td><%=data.getDeptName() %></td>
			<td><%=data.getMngId() %></td>
			<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td>
			<td>
				<button type="button" onclick="location.href='./depts/mod?id=<%=data.getDeptId()%>'">수정</button>
				<button type="button" onclick="location.href='./depts/del?id=<%=data.getDeptId()%>'">삭제</button>
			</td>
		</tr>
	
	<%
			}
		}
	%>
	</table>
	<div>
		<ul>
			<li><a href="">Prev</a></li>
			<%
				if(request.getAttribute("pageList") != null) {
					List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");	
					for(Integer n: pageList) {			
			%>
				<li><a href="./depts?page=<%=n %>"><%=n %></a></li>
			<%			
					}
				}
			%>
			<li><a href="">Next</a></li>
		</ul>
	</div>
	--%>
</body>
</html>