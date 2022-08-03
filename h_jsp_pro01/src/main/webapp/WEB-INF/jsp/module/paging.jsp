<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div>
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${datas.hasPrevPage()}">
				<li class="page-item">
					<a class="page-link bi bi-caret-left-fill" href="${pageUrl}?page=${prevPage }"></a>				
				</li>
			</c:when>
			<c:otherwise> 
				<li class="page-item disabled">
					<a class="page-link bi bi-caret-left-fill" href="#"></a>					
				</li>					
			</c:otherwise>
		</c:choose>
		<c:forEach items="${datas.getPages(data.currentPage - 2, data.currentPage + 2)}" var="item">
				<li class="page-item">
					<a class="page-link ${currentPage == pageList.get(item-1) ? ' active' : '' }" 
					href="${pageUrl}?page=${item}">${item}</a>
				</li>
		</c:forEach>

			<c:choose>
				<c:when test="${datas.hasNextPage()}">
					<li class="page-item">
						<a class="page-link bi bi-caret-right-fill" href="${pageUrl}?page=${datas.nextPage}"></a>				
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled">
						<a class="page-link bi bi-caret-right-fill" href="#"></a>
					</li>					
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