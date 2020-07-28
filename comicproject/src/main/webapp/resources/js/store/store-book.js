var isbn = document.getElementById("add-isbn");
var title = document.getElementById("add-title");
var authors = document.getElementById("add-authors");
var publisher = document.getElementById("add-publisher");
var point = document.getElementById("add-point");
var tot = document.getElementById("add-tot");
var cat = document.getElementById("add-cat");
var img = document.getElementById("add-img");
var add_btn = document.getElementById("add-btn");

isbn.oninput = loadBookData;
add_btn.onclick = addBookData;

function loadBookData(){
	var send_isbn = isbn.value;

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
					title.value = bookdata.documents[0].title;
					authors.value = bookdata.documents[0].authors;
					publisher.value = bookdata.documents[0].publisher;

					point.disabled = false;
					cat.disabled = false;
					tot.disabled = false;

					point.value = 0;
					tot.value = 0;

					img.setAttribute("src", bookdata.documents[0].thumbnail);
				}
			}
		});

	}
};

function addBookData(){
	if( isbn.value.length != 10 && isbn.value.length != 13 ){
		alert("isbn을 확인해주세요");
		return;
	}

	if( cat.value == "" ){
		alert("카테고리를 선택해주세요");
		return;
	}
	
	if( tot.value == "" ){
		alert("수량을 입력해주세요");
		return;
	}
	else if( tot.value < 1 ){
		alert("수량이 너무 적습니다");
		return;
	}

	if(point.value == ""){ 
		alert("대여료를 입력해주세요");
		return;
	}else if(point.value == 0){ 
		var con = confirm("수수료가 0원입니다 정말로 진행할까요?");
		if(con == false) return;
	}



	var param = "point="+point.value+"&isbn="+isbn.value+"&category="+cat.value+"&total="+tot.value;

	$.ajax({
		type: 'post',
		url: '/comic/store/register.do?'+param,
		contentType: 'application/json; charset=utf-8',
		dataType: 'text',
		success: function(result){
			console.log(result);

			if(result == 1) {
				alert("도서 추가가 완료되었습니다");
				window.location.replace("http://localhost:8080/comic/store/listbook.do");
			}
			else
				alert("이미 등록되었거나 아직 없는 ISBN입니다");
		}
	});
}