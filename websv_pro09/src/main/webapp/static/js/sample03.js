/**
 * 
 */
  var student = {
    name : "홍길동",
    age : 23,
    gender: "M"
};

function f1(){	
	 var res1= document.getElementById("res1");
	 res1.innerHTML += " var student = {" + "<br>";
	 res1.innerHTML += "    name : '홍길동',"+ "<br>";
	 res1.innerHTML += "    age : 23,"+ "<br>";
	 res1.innerHTML += "    gender: 'M'" + "<br>";
	 res1.innerHTML += " };";
}
 
 function f2() {	
	 var arr = new Array();
	 var res2 = document.getElementById("res2");
	 // student의 모든 요소를 불러오는 for..in문
	 for(let name in student) {
		let value = student[name];
		if(typeof(value) === 'string') {
			value = "'" + value + "'";
		}
	  	arr.push(name + ": " + value);		
	}
	 res2.innerHTML += "student -> "
	 res2.innerHTML += "{" + arr.join(", ") + "}" + "<br>";
	 res2.innerHTML += "student['name'] -> " + student['name']  + "<br>";
	 res2.innerHTML += "student.name -> " + student.name  + "<br>";
}

/* let을 이용해서 모든요소를 출력할 수 있다.
 for(let name in student) {
    console.log(name + " : " + student[name]);
};
 */
 
	// input 태그에 입력된 정보를 수출하여 stock 객체 생성하기
	// 만약 입력한 값이 없으면, res3 에 값을 입력하라는 메시지 출력.
 function f3() {
	var name = document.getElementById("id_input1_name");
	var count = document.getElementById("id_input1_count");
	var price = document.getElementById("id_input1_price");
	var res3 = document.getElementById("res3");
	
	if(!name.value) {
		res3.innerHTML = "주식명을 입력하세요."
		name.focus(); // focusing 시킴
		return;
	}
	if(!count.value) {
		res3.innerHTML = "주식수량을 입력하세요."
		count.focus(); // focusing 시킴
		return;
	}
	if(!price.value) {
		res3.innerHTML = "주식금액을 입력하세요."
		price.focus(); // focusing 시킴
		return;
	}	
	var data = genStock(name.value, parseInt(count.value), parseInt(price.value));
	res3.innerHTML = print(data);
}
 
 
 function genStock(name, count, price) {
	var stock = {
		name: name,
		count: count,
		price: price
	}
	return stock;
}
 
 
 function print(data) {
	var arr = new Array();
	for(let name in data) {
		let value = data[name];
		if(typeof(value) === 'string') {
			value = "'" + value + "'";
		} else if(typeof(value) === 'function'){
			value = "'" + value() + "'";
		}
		arr.push(name + ": " + value);
	};
	return "{" + arr.join(", ") + "}";	
}
 
 
 // 원하는 속성의 값을 변경하기
 function f4() {
	var name = document.getElementById("id_input2_name");
	var value = document.getElementById("id_input2_value");
	var res4 = document.getElementById("res4");
	
	if(!name.value) {
		res4.innerHTML = "속성명을 입력하세요."
		name.focus(); // focusing 시킴
		return;
	}
	if(!value.value) {
		res4.innerHTML = "속성값을 입력하세요."
		value.focus(); // focusing 시킴
		return;
	}
	
	student[name.value] = value.value;
	res4.innerHTML = print(student);
}
 
 
 
 
 function f5() {
	var width = document.getElementById("id_input3_width");
	var height = document.getElementById("id_input3_height");
	var color = document.getElementById("id_input3_color");
	var res5 = document.getElementById("res5");
	
	if(!width.value) {
		res5.innerHTML = "너비를 입력하세요."
		width.focus(); // focusing 시킴
		return;
	}
	if(!height.value) {
		res5.innerHTML = "높이를 입력하세요."
		height.focus(); // focusing 시킴
		return;
	}
	if(!color.value) {
		res5.innerHTML = "색상을 입력하세요."
		color.focus(); // focusing 시킴
		return;
	}
	var square = genArea(parseInt(width.value), parseInt(height.value), color.value);
	
	
// 사각형을 출력하는 방법 1
	var element = document.createElement("div");
	element.style.width= square.width + "px";
	element.style.height = square.height + "px";
	element.style.backgroundcolor = square.color;
	element.innerHTML = "이 사각형의 면적은 " + square.getArea() + " 입니다.";
	res5.appendChild(element); //추가(실행할때마다 추가적 생성)
	
	
	
/* 사각형을 출력하는 방법 2
	var element = "";
	element += "<div"
	element += " style='display:inline-block;";
	element += "width:" + square.width + "px;";
	element += "height:" + square.height + "px;";
	element += "background-color:" + square.color + ";'>";
	element += "면적이 " + square.getArea() + "인 사각형 입니다.";
	element += "</div>";
	res5.innerHTML = element;
*/
}
 

  function genArea(width=1, height=1, color="#000000") {
	var square = {
		width: width,
		height: height,
		color: color,
		getArea: () => {
			console
			return width * height;
		}
	}
	return square;
}
 
 
 