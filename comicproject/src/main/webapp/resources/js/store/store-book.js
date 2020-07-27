var isbn = document.getElementById("add-isbn");
var title = document.getElementById("add-title");
var authors = document.getElementById("add-authors");
var publisher = document.getElementById("add-publisher");
var point = document.getElementById("add-point");
var tot = document.getElementById("add-tot");
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

				if( bookdata.total == 0 ){ 
					alert("없는 isbn번호입니다");
					return;
				}else if( bookdata.total == 1 ){
					title.value = bookdata.items[0].title;
					authors.value = bookdata.items[0].author;
					publisher.value = bookdata.items[0].publisher;

					point.disabled = false;
					tot.disabled = false;

					point.value = 0;

					img.setAttribute("src", bookdata.items[0].image);
				}
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

	if( isbn.value.length == 10 || isbn.value.length == 13 ){
		
		var param = {"point" : point.value, "isbn" : isbn.value };
		
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