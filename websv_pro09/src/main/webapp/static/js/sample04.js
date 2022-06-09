/**
 * 
 */
 
 function f1() {
	var res1 = document.getElementById("res1");
	res1.innerHTML = new Date();
}

 function f2() {
	var res2 = document.getElementById("res2");
	res2.innerHTML = new Date('1 1, 2022');
	res2.innerHTML = new Date(2022, 0, 1);
	res2.innerHTML = new Date("2022-01-01")
}

function f3() {
	var res3 = document.getElementById("res3");
	var date = document.getElementById("id_input1_date");
	res3.innerHTML = new Date(date.value);
}