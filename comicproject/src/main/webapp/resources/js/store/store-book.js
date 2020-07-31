function loadBookData(form_data){

	var param = {pk :"" , isbn: ""};

	if( form_data["pk"] != null )
		param.pk = form_data["pk"].value;

	if( form_data["isbn"] != null )
		param.isbn = form_data["isbn"].value;

	if( (form_data["pk"] != null && param.pk.length != 19) ||
		(form_data["isbn"] != null && !( param.isbn.length == 10 || param.isbn.length == 13)) )
		return;
	
	$.ajax({
		type : 'post',
		url : "/comic/store/loadbookdata.do?pk="+param.pk+"&isbn="+param.isbn,
		dataType : "json",
		success : function(bookdata){

			if( bookdata.meta.total_count == 0 ){ 
				alert("없는 isbn번호입니다");
				return;
			}else if( bookdata.meta.total_count == 1 ){
				// 모달 공통 항목 초기화
				form_data["title"].value = bookdata.documents[0].title;
				form_data["authors"].value = bookdata.documents[0].authors;
				form_data["publisher"].value = bookdata.documents[0].publisher;

				form_data["point"].disabled = false;
				form_data["category"].disabled = false;

				form_data["thumbnail"].setAttribute("src", bookdata.documents[0].thumbnail);
				//////////////////////////////////////
				
				// 도서 추가 모달 항목 초기화
				if( form_data["total"] != null ){
					form_data["total"].disabled = false;
					form_data["total"].value = 0;
				}

				form_data["point"].value = 0;
				/////////////////////////////////////
				
				// 도서 수정/삭제 모달 항목 초기화
				if( bookdata.point != null )
					form_data["point"].value = bookdata.point;
				if( bookdata.category != null )
					form_data["category"].value = bookdata.category;
				if( bookdata.status != null ){
					form_data["status"].disabled = false;
					form_data["status"].value = bookdata.status;
				}
				///////////////////////////////////////////
				
			}
		}
	});

};

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
	
	var param = "point="+data["point"].value+
				"&pk="+data["pk"].value+
				"&category="+data["category"].value+
				"&status="+data["status"].value;

	$.ajax({
		type: 'post',
		url: '/comic/store/update.do?'+param,
		dataType: 'text',
		success: function(result){
			if(result == 1) {
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
	
	if( form_tag["pk"] != null )
		if( form_tag["pk"].value.length != 19 ){
			alert("관리번호를 확인해주세요");
			return false;
		}

	if( form_tag["category"].value == "" ){
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
		if( form_tag["status"].value == "" ){
			alert("상태정보를 확인해주세요");
			return false;
		}
	
	if( form_tag["point"].value == ""){ 
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