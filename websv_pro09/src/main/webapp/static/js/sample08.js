/**
 * 
 */
window.onload = function() {
	// .clone(true) 에 대한 이벤트 핸들러 복제는 JQuery로 연결된
	// 이벤트 핸들러에 대해서만 적용된다.
	// 기본 DOM의 addEventListener로 등록된 경우 동작하지 않는다.
	$(".copy1").bind("mouseover", function(){
			this.style.color = 'red';		
	});
	$(".copy1").bind("mouseout", function(){
			this.style.color = 'black';		
	});
	
	$("input").bind("input", function(){
		console.log(value);
	})
	
}