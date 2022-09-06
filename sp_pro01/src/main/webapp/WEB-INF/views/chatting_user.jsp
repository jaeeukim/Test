<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 채팅</title>
</head>
<body>
	<header class="mb-3">
		<%@ include file="./module/navigation.jsp" %>
	</header>
	<section class="container">
		<div style=height:500px; overflow:scroll;" id="id_chat"></div>
		<div>
			<form onsubmit="return sendMessage(this.context);">
				<input type="text" id="id_context" name="context">
				<button type="submit">전송</button>
			</form>
		</div>
	</section>
</body>
<script type="text/javascript">
	ws = new WebSocket("ws://localhost/spring/chatting/cs");
	ws.onopen = function(){
		console.log("Chatting Server Connection...");
	};
	
	// 메세지 수신 이벤트
	ws.onmessage = function(data){
		console.log(data);
		id_chat.innerHTML += data.data;
		id_chat.scrollTo(0, id_chat.scrollHeight);
	};
	ws.onclose = function() {
		console.log("Chatting Server Close...");
	};
	
	// 메세지 송신 이벤트
	function sendMeesage(element) {
		value = element.value;
		element.value = "";
		ws.send(value);
		element.focus();
		return false;
	}
</script>
</html>