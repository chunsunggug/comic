function addModalLoadBookData(form_data){

	var isbn = form_data["isbn"].value;

	if( !( isbn.length == 10 || isbn.length == 13) )
		return;
	
	$.ajax({
		type : 'post',
		url : "/comic/store/addloadbookdata.do?isbn="+isbn,
		dataType : "json",
		success : function(bookdata){

			if( bookdata == null ){ 
				alert("없는 isbn번호입니다");
				return;
			}else{
				form_data["title"].value = bookdata.title;
				form_data["authors"].value = bookdata.authors;
				form_data["publisher"].value = bookdata.publisher;
				
				if( bookdata.total > 0 ){
					form_data["point"].disabled = true;
					form_data["category"].disabled = true;
					form_data["point"].value = bookdata.point
					form_data["category"].value = bookdata.category;
				}else{
					form_data["category"].disabled = false;
					form_data["point"].disabled = false;
					form_data["category"].value = "";
					form_data["point"].value = 0;
				}

				form_data["total"].disabled = false;
				form_data["total"].value = 0;
				form_data["thumbnail"].setAttribute("src", bookdata.thumbnail);
			}
		}
	});

};

function updateModalLoadBookData(form_data){
	var pkisbn = form_data["pkisbn"].value;

	if( !( pkisbn.length == 10 || pkisbn.length == 13 || pkisbn.length == 16 || pkisbn.length == 19) )
		return;
	
	$.ajax({
		type : 'post',
		url : "/comic/store/updateloadbookdata.do?pkisbn="+pkisbn,
		dataType : "json",
		success : function(bookdata){

			if( bookdata == null ){ 
				alert("없는 isbn 또는 관리번호입니다");
				return;
			}else{
				form_data["title"].value = bookdata.title;
				form_data["authors"].value = bookdata.authors;
				form_data["publisher"].value = bookdata.publisher;
				form_data["thumbnail"].setAttribute("src", bookdata.thumbnail);

				if( bookdata.sbidx != null){
					// 관리번호를 적은 경우
					form_data["point"].disabled = true;
					form_data["category"].disabled = true;
					form_data["status"].disabled = false;
					
					form_data["point"].value = bookdata.point;
					form_data["category"].value = bookdata.category;
					form_data["status"].value = bookdata.status;
					
					form_data["delbtn"].classList.remove("disabled");
				}else{
					// isbn을 적은 경우
					form_data["point"].disabled = false;
					form_data["category"].disabled = false;
					form_data["status"].disabled = true;
					
					form_data["point"].value = bookdata.point;
					form_data["category"].value = bookdata.category;
					form_data["status"].value = bookdata.status;
					
					form_data["delbtn"].classList.add("disabled");
				}
			}
		}
	});
}

function addBookData(data){

	if( !checkValidate(data) ) return;
	
	var param = "point="+data["point"].value+
				"&isbn="+data["isbn"].value+
				"&category="+data["category"].value+
				"&total="+data["total"].value;

	$.ajax({
		type: 'post',
		url: '/comic/store/register.do?'+param,
		dataType: 'text',
		success: function(result){
			if(result == 1) {
				alert("도서 추가가 완료되었습니다");
				window.location.replace("http://localhost:8080/comic/store/listbook.do");
			}
			else
				alert("이미 등록되었거나 아직 없는 ISBN입니다");
		}
	});
}

function updateBookData(data){
	
	if( !checkValidate(data) ) return;
	
	var leng = data["pkisbn"].value.length;
	
	var param = "point=" + data["point"].value +
				"&category=" + data["category"].value +
				"&status=" + data["status"].value;
	
	if( leng == 10 || leng == 13 ) // isbn 적은 경우
		param += "&isbn=" + data["pkisbn"].value;
	else if( leng == 16 || leng == 19 ) // 관리번호 적은 경우
		param += "&sbidx=" + data["pkisbn"].value;

	$.ajax({
		type: 'post',
		url: '/comic/store/update.do?'+param,
		dataType: 'text',
		success: function(result){
			if(result >= 1) {
				alert("수정이 완료되었습니다");
				window.location.replace("http://localhost:8080/comic/store/listbook.do");
			}
			else
				alert("없는 도서입니다");
		}
	});
}

function checkValidate(form_tag){
	if( form_tag["isbn"] != null)
		if( form_tag["isbn"].value.length != 10 && form_tag["isbn"].value.length != 13 ){
			alert("isbn을 확인해주세요");
			return false;
		}
	
	if( form_tag["pkisbn"] != null )
		if( !(form_tag["pkisbn"].value.length == 10 ||
			form_tag["pkisbn"].value.length == 13 ||
			form_tag["pkisbn"].value.length == 16 ||
			form_tag["pkisbn"].value.length == 19) ){
			alert("관리번호를 확인해주세요");
			return false;
		}

	if( form_tag["category"].value == "" &&
		!form_tag["category"].disabled ){
		alert("카테고리를 선택해주세요");
		return false;
	}
	
	if( form_tag["total"] != null){
		if( form_tag["total"].value == "" ){
			alert("수량을 입력해주세요");
			return false;
		}
		else if( form_tag["total"].value < 1 ){
			alert("수량이 너무 적습니다");
			return false;
		}
	}
	
	if( form_tag["status"] != null )
		if( form_tag["status"].value == "" &&
			!form_tag["status"].disabled ){
			alert("상태정보를 확인해주세요");
			return false;
		}
	
	if( !form_tag["status"].disabled )
		if( form_tag["point"].value == "" ){ 
			alert("대여료를 입력해주세요");
			return false;
		}else if( form_tag["point"].value == 0){ 
			var con = confirm("수수료가 0원입니다 정말로 진행할까요?");
			if(con == false) return false;
		}

	return true;
}

function deleteItem(item){
	var con = confirm("정말로 삭제할까요?");
	if( con == false )
		return;
	
	var param = "sbidx=" + item;

	$.ajax({
		type: 'post',
		url: '/comic/store/delete.do?'+param,
		contentType: 'application/json; charset=utf-8',
		dataType: 'text',
		success: function(result){
			if(result == 1) {
				alert("삭제가 완료되었습니다");
				window.location.replace("http://localhost:8080/comic/store/listbook.do");
			}
			else
				alert("없는 도서입니다");
		}
	});
}