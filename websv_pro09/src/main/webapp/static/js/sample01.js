/**
 * 
 */
 
 
 // var arr1 =[1, 2, 3];
 var arr1 = new Array(1, 2, 3); //동일함
 var res1 = document.getElementById("res1");
 res1.innerHTML += arr1 + "<br>";
 res1.innerHTML += "arr1[0] = " + arr1[0] + "<br>";
 res1.innerHTML += "arr1[1] = " + arr1[1] + "<br>";
 res1.innerHTML += "arr1[2] = " + arr1[2] + "<br>";
 
 
 
 arr1[0] = 10;
 arr1[1] = 20;
 arr1[2] = 30;
 res1.innerHTML += arr1 + "<br>";
 
 // .indexof 인덱스번호 반환
 res1.innerHTML += "arr1.indexof(10) ->" + arr1.indexOf(10) + "<br>";
 res1.innerHTML += "arr1.indexof(20) ->" + arr1.indexOf(20) + "<br>";
 res1.innerHTML += "arr1.indexof(20) ->" + arr1.indexOf(30) + "<br>";
 
 // .push 뒤에 추가로 붙임 ''사용하면 문자열로 들어감
 res1.innerHTML += "arr1.push(40)" + "<br>"; 
 arr1.push(40);
 res1.innerHTML += "arr1.push(50)" + "<br>";
 arr1.push(50);
 res1.innerHTML += "arr1.push(60)" + "<br>";
 arr1.push(60);
 
 res1.innerHTML += arr1 + "<br>";

// .unshift 앞에 추가로 붙임
 res1.innerHTML += "arr1.unshift(0)" + "<br>";
 arr1.unshift(0);
 
 res1.innerHTML += arr1 + "<br>";
 
 // .pop 맨뒤에 요소를 제거 (반환도 함)
 res1.innerHTML += "arr1.pop()" + "<br>";
 arr1.pop();
 
 res1.innerHTML += arr1 + "<br>";
 
 // .shift 맨 앞에 요소를 제거 (반환도 함)
 res1.innerHTML += "arr1.shift()" + "<br>";
 arr1.shift();
 
 res1.innerHTML += arr1 + "<br>";
 
 // .reverse 배열 역순 정렬
 res1.innerHTML += "arr1.reverse()" + "<br>";
 arr1.reverse();
 
 res1.innerHTML += arr1 + "<br>";
 
 // .sort 배열 정렬
 res1.innerHTML += "arr1.sort()" + "<br>";
 arr1.sort();
 
 res1.innerHTML += arr1 + "<br>";
 
 
 arr1.sort(function(x, y) { return y - x; });
 
 
 

 // .slice (시작, 끝) 시작과 끝 index 요소만 잘라오기
 res1.innerHTML += "arr1.slice(0, 3)" + "<br>";
 arr1 = arr1.slice(0, 3);
 
 res1.innerHTML += arr1 + "<br>";
  
 // .concat 배열 결합
 res1.innerHTML += "arr1.concat(['a', 'b', 'c'])" + "<br>";
 arr1.concat(['a', 'b', 'c']);
 
 res1.innerHTML += arr1 + "<br>";
 
 // .toString 문자열로 반환
 arr1.toString();
 
 // .join ""안에 있는값을 기준으로 결합, 문자열로 반환
 arr1.join(", ");
 
 
/*
 * 문제풀기
 * 다음의 input 요소에 있는 값을 배열로 만들어 exam1 에 출력
 * 출력 형식은 ['a', 'b', 'c', 'd', 'e'] 와 같이 대괄호 안에 값이 출력되도록 한다. 
 */
 
 var arr2;
 var input1 = document.getElementById("id_input1");
 var exam1 = document.getElementById("exam1");
 arr2 = input1.value.split(", ");
 
 for(var i = 0; i < arr2.length; i++) {
	arr2[i] = arr2[i].trim();
}

exam1.innerHTML = "['" + arr2.join("', '") + "']";
 
/*
 * 다음의 input 요소에 있는 값을 문제 1에서 만든 배열에 추가후 출력<br>
 * 출력 형식은 문제 1과 동일하다.
 * 추가로 input 요소에 있는 값의 총 합을 구하여 배열에 추가도 한다.
 */
 var input2 = document.getElementById("id_input2");
 var exam2 = document.getElementById("exam2");
 var temp = input2.value.split(", ");
 var total = 0;
 
 for(let item of temp) {
	item = parseInt(item);
	total += item;
	arr2.push(item);
}
 arr2.push(total);
 
 exam2.innerHTML = "['" + arr2.join("', '") + "']";
 
 
 
 
 