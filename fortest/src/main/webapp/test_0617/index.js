/**
 * 
 */
 
function f1() {
	var id1 = document.getElementById("id1");
	var req1 = document.getElementById("req1");
	id1.addEventListener("blur", function() {
		if(id1.value === "") {
			req1.innerHTML = "필수정보입니다.";
		}
	})
}

function f2() {
	var pw1 = document.getElementById("need-chk");
	var req2 = document.getElementById("surprise");
	pw1.addEventListener("blur", function() {
		if(pw1.value === "") {
			req2.innerHTML = "필수정보입니다.";
		}
	})	
}

function f3() {
	var pw2 = document.getElementById("pw2");
	var req3 = document.getElementById("req3");
	pw2.addEventListener("blur", function() {
		if(pw2.value === "") {
			req3.innerHTML = "필수정보입니다.";
		}		
	})	
}
 
 