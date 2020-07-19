
var isbn = document.getElementById("add-isbn");
var title = document.getElementById("add-title");
var authors = document.getElementById("add-authors");
var publisher = document.getElementById("add-publisher");
var fee = document.getElementById("add-fee");
var tot = document.getElementById("add-tot");
var img = document.getElementById("add-img");
var add_btn = document.getElementById("add-btn");

isbn.oninput = loadBookData;
//add_btn.onclick = addBookData;

function loadBookData(){
	var send_isbn = isbn.value;
	
	if( send_isbn.length == 13 || send_isbn.length == 10 ){ 
		
		$.ajax({
			type : 'post',
			url : "/comic/store/loadbookdata.do",
			data : send_isbn,
			dataType : "json",
			success : function(bookdata){
				
				title.value = bookdata.title;
				authors.value = bookdata.authors;
				publisher.value = bookdata.publisher;
				
				fee.disabled = false;
				tot.disabled = false;
				
				img.setAttribute("src", bookdata.thumbnail);
			}
		})

	}
	else{
		
	}
};

function addBookData(){
	
	
	if(fee.value == ""){ 
		alert("대여료를 입력해주세요");
		return;
	}else if(fee.value == 0){ 
		var con = confirm("수수료가 0원입니다 정말로 진행할까요?");
		if(con == false) return;
	}
	
	if(tot.value == ""){
		alert("책의 권 수를 입력해주세요");
		return;
	}else if(tot.value == 0){ 
		alert("책의 권 수가 0은 사용할 수 없습니다");
		return;
	}else{
		alert("ajax 입력해주세요");
		var send_data;
		send_data.isbn = isbn.value;
		send_data.fee = fee.value;
		send_data.tot = tot.value;
		
		console.log("what?");
		
		$.ajax({
			type: 'post',
			url: '/comic/store/register.do',
			data: send_data,
			dataType: 'json',
			success: function(result){
				console.log("succ");
			}
		})
	}
	


	
}