<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="paging">
	<c:set var="currentPage" value="${page }"/>
	<c:set var="prevPage" value="${currentPage - 1}"/>
	<c:set var="nextPage" value="${currentPage + 1 }"/>
	<c:set var="maxPage" value="${pageList.get(pageList.size() -1) }"/>
	
	
	<ul class="page center">
		<li class="page-item">
			<c:choose>
				<c:when test="${prevPage <= 0 }">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>					
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${prevPage }">keyboard_arrow_left</a>				
				</c:otherwise>
			</c:choose>
		</li>

		<c:forEach begin="${currentPage - 2 <= 0 ? 1 : currentPage - 2}" end="${currentPage + 2  > maxPage ? maxPage : currentPage + 2}" var="item">
				<li class="page-item">
					<a class="page-link ${currentPage == pageList.get(item-1) ? ' active' : '' }" 
					href="${pageUrl}?page=${pageList.get(item -1)}">${pageList.get(item-1) }</a>
				</li>
		</c:forEach>

		<li class="page-item">
			<c:choose>
				<c:when test="${nextPage > pageList.size()}">
					<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>					
				</c:when>
				<c:otherwise>
					<a class="page-link material-symbols-outlined" href="${pageUrl}?page=${nextPage }">keyboard_arrow_right</a>				
				</c:otherwise>
			</c:choose>
			
		</li>
	</ul>
</div>
		<%-- EL/JSTL 사용으로 필요 없어짐.
			<%
			List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
			int currentPage = (int)request.getAttribute("page");
			int prevPage = currentPage - 1;
			int nextPage = currentPage + 1;
			%>
		--%>
		<%-- EL/JSTL로 대신함.
			<% if(prevPage <= 0) { %>
				<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
			<% } else { %>
				<a class="page-link material-symbols-outlined" href="./depts?page=<%=prevPage %>">keyboard_arrow_left</a>
			<% } %>
		 --%>
		<%-- EL/JSTL로 대신함.
			<%
				for(Integer n: pageList) {
			%>
					<li class="page-item"><a class="page-link<%=currentPage == n ? " active" : "" %>" href="./depts?page=<%=n %>"><%=n %></a></li>
			<%
				}
			%>
		 --%>
		<%-- EL/JSTL로 대신함.
			<% if(nextPage > pageList.size()) { %>
				<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
			<% } else { %>
				<a class="page-link material-symbols-outlined" href="./depts?page=<%=nextPage %>">keyboard_arrow_right</a>
			<% } %>
		 --%>