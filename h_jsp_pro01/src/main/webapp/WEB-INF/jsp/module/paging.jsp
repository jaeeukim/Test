<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<div class="paging">
	<%
		List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
		int currentPage = (int)request.getAttribute("page");
		int prevPage = currentPage - 1;
		int nextPage = currentPage + 1;
	%>
	<ul class="page center">
		<li class="page-item">
			<% if(prevPage <= 0) { %>
				<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_left</a>
			<% } else { %>
				<a class="page-link material-symbols-outlined" href="./depts?page=<%=prevPage %>">keyboard_arrow_left</a>
			<% } %>
		</li>
		<%
			for(Integer n: pageList) {
		%>
				<li class="page-item"><a class="page-link<%=currentPage == n ? " active" : "" %>" href="./depts?page=<%=n %>"><%=n %></a></li>
		<%
			}
		%>
		<li class="page-item">
			<% if(nextPage > pageList.size()) { %>
				<a class="page-link disabled material-symbols-outlined" href="#">keyboard_arrow_right</a>
			<% } else { %>
				<a class="page-link material-symbols-outlined" href="./depts?page=<%=nextPage %>">keyboard_arrow_right</a>
			<% } %>
		</li>
	</ul>
</div>