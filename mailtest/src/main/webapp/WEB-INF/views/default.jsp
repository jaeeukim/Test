<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메일전송</title>
</head>
<body>
	<form action="sendMail_ok.jsp" method="post">
		<table border="1" align="center" style="font-size: 10pt;" cellpadding="0" cellspacing="0">
			<tr>
				<td>보내는사람</td>
				<td><input type="text" name="senderName" size="50"></td>
			</tr>
			<tr>
				<td>보내는사람 email</td>
				<td><input type="text" name="senderEmail" size="50"></td>
			</tr>
			<tr>
				<td>받는사람 email</td>
				<td><input type="text" name="receiverEmail" size="50"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" size="50"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><textarea rows="8" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit"value ="메일보내기">
				</td>
			</tr>
		
		</table>
		
	</form>

</body>
</html>