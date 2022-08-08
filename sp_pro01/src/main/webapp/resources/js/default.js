
function getContextPath() {
	let contextPath = "jsp01/";  //강사님과 달리 contextPath존재
	$.ajax({
		type: "get",
		url: "/get/contextpath",
		dataType: "json",
		success: function(data) {
			contextPath = data.contextpath;
		}
	});
	return contextPath;
}

 function ajaxUploadImage(e) {
	var file = e.target.files[0];
	var fData = new FormData();
	fData.append("uploadImage", file, file.name);
	
	$.ajax({
		type: "post",
		url: "/jsp01/ajax/imageUpload", // 강사님과 달리 contextroot 필요함 
		enctype: "multipart/form-data",
		data: fData,
		processData: false,		// 문자열 제외 데이터 전송할때 false (like 이미지)(기본 true)
		contentType: false,		
		success: function(data, status) {
			prevImage.src = data.loc;
		},
		error: function(data, status) {
			prevImage.src = data.loc;
		}
	});
}

function showPreview(element, id) {
	var file = element.files[0]; 		// 선택한 이미지의 파일 객체 정보가 저장되는 곳 (파일이 여러개일 수 있으니까 배열로 받아서 한개 가져오기)
	var imgUrl = URL.createObjectURL(file);		// 파일에 대한 URL 정보를 가져옴 
	var img = document.getElementById(id);
	img.src = imgUrl;						// 해당 이미지의 경로 정보를 prevImage.src에 저장시킴
}

function enableSaveButton(e) {
	var submit = document.querySelector("button[type='submit']");
	var enable = submit.getAttribute("class").replace("disable", "");
	submit.setAttribute("class", enable);
}

function sendElementDataValid(element, url) {
	$.ajax({
		type: "get",
		url: url,
		data: {
			name: element.name,
			value: element.value
		},
		success: function(data, status) {
			setLabelState(element.nextElementSibling, data.code, data.message);
		}, 
		complete: function() {
			if(element.value === "" || element.value === undefined) {
				element.nextElementSibling.innerText = "";
			}		
		}
	});
}


function duplicateCheck(element, url) {
	sendElementDataValid(element, url)
}

function existsCheck(element, url) {
	sendElementDataValid(element, url)
}

function setLabelState(element, code, message) {
		if(code === "success") {
			// 정상 처리 메시지
			element.innerText = message;
			element.setAttribute("class", "input-label-ok")
			
		} else if(code === "error") {
			// 오류 메시지 
			element.innerText = message;
			element.setAttribute("class", "input-label-error")
			
		}
	
}