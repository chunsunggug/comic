function loadBookData(form_data){
	
	var send_isbn = form_data["isbn"].value;
	
	if( send_isbn.length == 13 || send_isbn.length == 10 ){ 

		$.ajax({
			type : 'post',
			url : "/comic/store/loadbookdata.do",
			data : send_isbn,
			dataType : "json",
			success : function(bookdata){

				if( bookdata.meta.total_count == 0 ){ 
					alert("없는 isbn번호입니다");
					return;
				}else if( bookdata.meta.total_count == 1 ){
					form_data["title"].value = bookdata.documents[0].title;
					form_data["authors"].value = bookdata.documents[0].authors;
					form_data["publisher"].value = bookdata.documents[0].publisher;

					form_data["point"].disabled = false;
					form_data["category"].disabled = false;
					form_data["total"].disabled = false;

					form_data["point"].value = 0;
					form_data["total"].value = 0;
					
					if(bookdata.point != null)
						form_data["point"].value = bookdata.point;
					if(bookdata.total != null)
						form_data["total"].value = bookdata.total;
					if(bookdata.category != null)
						form_data["category"].value = bookdata.category;

					form_data["thumbnail"].setAttribute("src", bookdata.documents[0].thumbnail);
				}
			}
		});

	}
};

function addBookData(data){

	if( !checkValidate(data) ) return;
	
	var param = "point="+data["point"].value+"&isbn="+data["isbn"].value+"&category="+data["category"].value+"&total="+data["total"].value;

	$.ajax({
		type: 'post',
		url: '/comic/store/register.do?'+param,
		contentType: 'application/json; charset=utf-8',
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
	
	var param = "point="+data["point"].value+"&isbn="+data["isbn"].value+"&category="+data["category"].value+"&total="+data["total"].value;

	$.ajax({
		type: 'post',
		url: '/comic/store/update.do?'+param,
		contentType: 'application/json; charset=utf-8',
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

function deleteBookData(data){
	if( data["isbn"].value.length != 10 && data["isbn"].value.length != 13 ){
		alert("isbn을 확인해주세요");
		return false;
	}
	
	var param = "isbn="+data["isbn"].value;

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

function checkValidate(form_tag){
	if( form_tag["isbn"].value.length != 10 && form_tag["isbn"].value.length != 13 ){
		alert("isbn을 확인해주세요");
		return false;
	}

	if( form_tag["category"].value == "" ){
		alert("카테고리를 선택해주세요");
		return false;
	}
	
	if( form_tag["total"].value == "" ){
		alert("수량을 입력해주세요");
		return false;
	}
	else if( form_tag["total"].value < 1 ){
		alert("수량이 너무 적습니다");
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