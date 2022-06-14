/**
 * 
 */
// 체크박스 전체 선택/해제
 function toggleAll(element){
 	var name = element.getAttribute("name");
 	var chk_items = document.getElementsByName(name);
 	
 	if(element.getAttribute("checked") === "") { //체크푼다면 전체체크 해제
 		for(e of chk_items){
 			e.removeAttribute("checked");
 		}
 	} else {
	 	for(e of chk_items){					// 체크되면 전체 체크
	 		e.setAttribute("checked", "");
 		}
 	}
 }
 
 // 1 ~12월 까지의 select/option 자동생성
 window.onload = function() {
	let month_selects = document.querySelectorAll("select.sel-month");
	
	for(e of month_selects){
		createOptionMonth(e);
	}
}
 
 function createOptionMonth(element){
	for(let i = 1; i <= 12; i++) {
		let option = document.createElement("option");
		option.setAttribute("value", i);
		option.innerText = `${i}월`;  // `은 1옆에있는 특수문자
		element.append(option);
	}
}

function createOptionDate(e1, e2){
	let month = e1.value;
	let date = new Date();
	date.setMonth(month, 0); //setMonth(month, 0)은 해당월 -1월에 마지막날출력
							//setMonth(0, 0)은 1월의 전달인 12월의 마지막날인 31일 출력
	
	let count = e2.childElementCount;
	if(count > 1) {
		let opts = e2.children;
		for(let idx = 1; idx < count; idx++){
			e2.removeChild(opts[1]);	//1을 하는 이유는 '일'을 0인덱스에 두고 나머지를 지우기 위해서		
		}
	}
	
	for(let d = 1; d <= date.getDate(); d++){ //date.getDate()가 마지막날을 알려고 쓰인것
		let option = document.createElement("option");
		option.setAttribute("value", d);
		option.innerText = `${d}일`;  // `은 1옆에있는 특수문자
		e2.append(option);
	}
}










 