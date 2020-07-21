var isbn = document.getElementById("add-isbn");
var title = document.getElementById("add-title");
var authors = document.getElementById("add-authors");
var publisher = document.getElementById("add-publisher");
var point = document.getElementById("add-point");
var tot = document.getElementById("add-tot");
var img = document.getElementById("add-img");
var add_btn = document.getElementById("add-btn");
var save_isbn;

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

				if(bookdata == null){ 
					alert("없는 isbn번호입니다");
					return;
				}
				
				save_isbn = bookdata.isbn;
				
				title.value = bookdata.title;
				authors.value = bookdata.authors;
				publisher.value = bookdata.publisher;

				point.disabled = false;
				tot.disabled = false;

				point.value = 0;

				img.setAttribute("src", bookdata.thumbnail);
			}
		});

	}
};

function addBookData(){

	if(point.value == ""){ 
		alert("대여료를 입력해주세요");
		return;
	}else if(point.value == 0){ 
		var con = confirm("수수료가 0원입니다 정말로 진행할까요?");
		if(con == false) return;
	}

	var save_isbn_split = save_isbn.split(' ');
	if( save_isbn_split[0].length == 10 || save_isbn_split[1].length == 13 ){
		
		var param = {"point" : point.value, "isbn" : save_isbn };
		
		$.ajax({
			type: 'post',
			url: '/comic/store/register.do',
			data: JSON.stringify(param),
			contentType: 'application/json; charset=utf-8',
			dataType: 'text',
			success: function(result){
				console.log(result);
				
				if(result == 1) {
					alert("도서 추가가 완료되었습니다");
					window.location.replace("http://localhost:8080/comic/store/listbook.do");
				}
			}
		});
	}
}