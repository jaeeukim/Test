<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- jstl의 tag기능을 사용하기 위해서 taglib를 등록했다. 
     가독성과 코드량에서 이득을 볼 수 있다.-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>	
	<%@ include file = "./module/head.jsp" %>
	<link rel="shortcut icon" href="#">
</head>
<script type="text/javascript">
	function sendAjax() {
		$.ajax({
			type: "get",  // method방식 - POST도 가능 
			url: "/jsp01/ajax/test", //Ajax를 처리할 서버 주소
			data: {
				x: 1, y:"A"
			},
			dataType: "json", // 서버로 부터 전달 받을 데이터 타입(json, text, xml, html...)
			success: function(data, status) { // 인자가 필요함
				// 응답이 성공(응답코드 200일때)적으로 이루어졌을때 동작할 함수
				console.log("success: " + data);
				for(d of data) {
					console.log("success: " + data.msg);
					console.log("success: " + data.kor);
					console.log("success: " + data.empId);
					console.log("success: " + data.deptName);					
				}
			},
			error: function(data, status){
				// 응답코드가 200이 아닌 모든 응답일 때 동작할 함수
				console.log(data);
				console.log(status);
			},
			complete: function() {
				// 성공/실패 여부와 관계없이 동작할 함수
				console.log("complete 무조건 실행");
			},
			beforeSend: function() {
				// 서버에 데이터를 전송하기 전에 동작할 함수
				console.log("beforeSend 데이터 전송 전");
			}
		});
	}
</script>



<body>
	<%-- <jsp:incliude page="./module/navigation.jsp" %> 아래 식과 동일함 (jsp:include 액션 태그)--%>
	<%@ include file="./module/navigation.jsp" %> <!-- inlcude 지시자 -->
	<!-- 액션태그 : 실행된 결과 코드를 삽입 (동적페이지에 사용) -분산효과
	     지시자   : 코드를 삽입한 후에 실행 (정적페이지에 사용) -->

	<section class="container">
		<div>
			<button type="button" onclick="sendAjax()">전송</button>	
		</div>
		<c:url var="loginUrl" value="/login"/>
		<form class="small-form" action="${loginUrl }" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input type="text" class="input-text" name="empId" value="" data-required="직원 ID를 입력하세요.">
				<c:if test="${not empty error }">
					<label class="input-label-error">${error }</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId" data-required="부서명을 선택하세요.">
					<c:forEach items="${deptList}" var="deptDto">
						<c:choose>
							<c:when test="${empty error and cookie.deptRe.value == deptDto.deptId }">
								<option value="${deptDto.deptId }" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>								
							</c:when>
							<c:when test="${not empty error and param.deptId ==  deptDto.deptId}">
								<option value="${deptDto.deptId }" selected>
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${deptDto.deptId }">
									[${deptDto.deptId}] ${deptDto.deptName}
								</option>
							</c:otherwise>
						</c:choose>
							<%-- <option value="${deptDto.deptId}" ${deptDto.deptId == param.deptId? '' : 'selected' }>[${deptDto.deptId}]${deptDto.deptName}</option> --%>
					</c:forEach>
				</select>
			</div>
			<div class="input-form wide">
				<label class="input-label">이름</label>
				<input type="text" class="input-text" name="empName" value="${param.empName }" data-required="이름을 입력하세요.">
			</div>
			<div class="input-form wide form-right">
					<input type="checkbox" value="on" name="deptRe"> 부서명 기억하기
				<button class="btn btn-outline btn-ok" type="submit">로그인</button>
			</div>
		</form>
	</section>





	<%-- 로그인 기능 구현을 위해 주석처리를 한다.
	<h1>Welcome JSP/Servlet</h1>	
		 <c:xxx> 제어문 
		 <fmt:xxx> 포멧 (날짜, 숫자)
		 ${fn:xxx } 함수(문자열)
	
	<c:if test="조건식(EL 사용)">
		
	</c:if>
	<c:choose>
		<c:when test="${param.x == 'a'}">
			파라미터 x의 값이 a면 실행 
		</c:when>
		<c:when test="${param.x == 'b' }">
			파라미터 x의 값이 b면 실행 
		</c:when>
		<c:when test="${param.x == 'c' }">
			파라미터 x의 값이 c면 실행 
		</c:when>
		<c:otherwise>
			모든 when 조건에 해당하지 않으면 실행됨
		</c:otherwise>	
	</c:choose>
	
	<hr>
	
	
	<ul>
		<c:forEach begin="1" end="5" var="v">
			<li>${v}</li>
		</c:forEach>
	</ul>
	<br>
	<%
		List<String> lst = new ArrayList<String>();
		lst.add("a"); lst.add("b"); lst.add("c"); lst.add("d");
		request.setAttribute("lst", lst);
	%>
	<ul>
		<c:forEach items="${lst}" var="v">
			<li>${v}</li>
		</c:forEach>
	</ul>
	<br>
	
	<%
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "가"); map.put("b", "나"); map.put("c", "다");
		request.setAttribute("map", map);
	%>
	<ul>
		<c:forEach items="${map}" var="v">
			<li>${v}</li> 
		</c:forEach>
	</ul>
	<!-- map의 경우 v로만 출력하면 'a=가' 로 나옴.
	     v.key로 출력하면 a,b,c같은 키만 / v.value하면 가,나,다 같은 값만 나온다-->
	
	
	
	<!-- scope 영역 설정 
	     page : 한 페이지 영역에서의 지역 변수
	     request : 하나의 요청 영역에서 사용 가능 (사용자가 요청->지나는 서버 내부 가능) - 요청간의 공유 안됨
	     session : 하나의 세션영역 (request에서 요청간의 정보가 공유될 수 있게함)  - 톰캣 프로세스 안에서 공유 안됨
	     application : (session에서 하나의 톰캣프로세스안에서 공유 될수있게함)
	     접근은 page부터해서 출력함 (같은 var명이면 page부터)
	 --!>
	<c:set var="data" value="Hello1" scope="page"/> <!-- setAttribute대신 사용 가능 --!>
	<c:set var="data" value="Hello2" scope="request"/> 
	<c:set var="data" value="Hello3" scope="session"/> 
	<c:set var="data" value="Hello4" scope="application"/> 
	${data }<br>
	${data }<br>
	${data }<br>
	${applicationScope.data }<br> <!-- 이런식으로 지정도 가능함 -->
	
	<c:set var="arr"> <!-- 배열도 가능한 -->
		가, 나, 다, 라
	</c:set>
	${arr }<br>
	
	<!-- set한거 지우기 -->
	<c:remove var="data" scope="page"/> 
	<c:remove var="data" scope="request"/> 
	<c:remove var="data" scope="session"/> 
	<c:remove var="data" scope="application"/> 
	${pageScope.data }<br>
	${requestScope.data}<br>
	${sessionScope.data }<br>
	${applicationScope.data }<br>
	
	<hr>
	
	<c:url var="url" value="./depts">
		<c:param name="x" value="Hello" />
		<c:param name="y" value="Hello" />
	</c:url>
	
	<!-- fmt---------------------------------------------------------------------- -->
	<!-- 숫자 관련 포멧 -->
	<fmt:formatNumber value="1000" /><br>   <!-- 1,000 -->
	<fmt:formatNumber value="0.1"  type="percent"/><br>   <!-- 10% -->
	<fmt:formatNumber value="1000"  type="percent"/><br>  <!-- 100,000% -->
	<fmt:formatNumber value="1000"  type="currency"/><br>  <!-- \1,000 (원화) 지역에 따라 다름 -->
	<fmt:formatNumber value="1000"  type="currency" currencySymbol="$"/><br>  <!-- $1,000 (원화) 지역에 따라 다름 -->
	
	<hr>
	<% 
		Date date = new Date(); 
		request.setAttribute("date", date);
	%>
	
	<!--  날짜 관련 포멧 -->
	<fmt:formatDate value="${date }" type="date" /><br>					 <!-- 2022. 7. 8 -->
	<fmt:formatDate value="${date }" type="date" dateStyle="full"/><br>  <!-- 2022년 7월 8일 금요일 -->
	<fmt:formatDate value="${date }" type="date" dateStyle="long"/><br>  <!-- 2022년 7월 8일 -->
	<fmt:formatDate value="${date }" type="date" dateStyle="medium"/><br><!-- 2022. 7. 8 -->
	<fmt:formatDate value="${date }" type="date" dateStyle="short"/><br> <!-- 22. 7. 8 -->
	<fmt:formatDate value="${date }" type="date" pattern="YYYY-MM-dd E EEEE"/><br> <!-- 2022-07-08 금 (pattern으로 원하는 형식지정가능)-->
	
	<hr>
	
	<!-- 시간 관련 포멧 -->
	<fmt:formatDate value="${date }" type="time" /><br>					 <!-- 오전 11:41:00 -->
	<fmt:formatDate value="${date }" type="time" timeStyle="full"/><br>  <!-- 오전 11시 41분 0초 대한민국 표준시 -->
	<fmt:formatDate value="${date }" type="time" timeStyle="long"/><br>  <!-- 오전 11시 41분 0초 KST -->
	<fmt:formatDate value="${date }" type="time" timeStyle="medium"/><br><!-- 오전 11:41:00 -->
	<fmt:formatDate value="${date }" type="time" timeStyle="short"/><br> <!-- 오전 11:41 -->
	<fmt:formatDate value="${date }" type="time" pattern="a hh:mm:ss.SSS Z z zzzz"/><br> <!-- (pattern으로 원하는 형식지정가능)-->
	<hr>
	
	<!-- 날짜, 시간 동시 포멧 -->
	<fmt:formatDate value="${date}" type="both"/><br>             <!-- 2022. 7. 8. 오전 11:47:55  -->
	<fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="short"/><br> <!-- 2022년 7월 8일 금요일 오전 11:47 -->
	 --%>
	
</body>
</html>