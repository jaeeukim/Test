/**
 * 자바스크립트는 동적 타입을 지원하기 때문에 자료형에 대해 명시할 필요 없음.
 */
 
 
// 선언적 함수
function f1() {
	return "함수 실행이 완료되었습니다.";
}
 
function f2() {
	return 100;
}

// return 미지정시 undefined 출력
function f3() {
	return;
}

// 익명함수
var f4 = function() {
	return "익명함수입니다.";
};
 
// 스스로 동작 함수 (호출 없이 바로 실행)
(function(){
	console.log("즉시 실행되는 함수 입니다.");
})();
 
 
 // 매개변수 개수 테스트
function f5(x) {
	console.log("매개변수 x 값 -> " + x);
}
 
// 기본값을 가지는 매개변수
function f6(x, y=0) {
	console.log("매개변수 x, y 값 -> " + x + ", " + y);
}


// 가변매개변수를 사용한 함수 (가변인자가 배열로)
function f7(x, ...args){
	console.log("매개변수 x 값 -> " + x);
	for(let arg of args) {
		console.log("매개변수 args 값 -> " + arg);
	}
}
/* 
	console.log(typeof(args)); -> object 타입
	console.log(args[0]);
*/
 
 
// 가변매개변수, 기본값 동시에 가지는 함수
function f8(x, y=0, ...args) {
	console.log("매개변수 x, y 값 -> " + x + ", " + y);
	for(let arg of args) {
		console.log("매개변수 args 값 -> " + arg);
	}
}
 
// 함수에 내장된 arguments
function f9() {
	console.log(arguments);
}
 
 
 
 
 
 
 
 
 
 
 