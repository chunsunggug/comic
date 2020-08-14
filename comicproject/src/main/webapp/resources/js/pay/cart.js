function addCart(sidx, isbn){
	
	$.ajax({
		url : "/comic/additemtocart.do?sidx="+sidx+"&isbn="+isbn,
		success : function(result) {
			if(result == 1){
				if(confirm("추가되었습니다. 카트로 이동하시겠습니까?")){
					location.href = "/comic/order/cart.do";
				}
			}
		}
	});
		
}

function deleteCartItem(sidx,isbn){
	$.ajax({
		url : "/comic/deletecartitem.do?sidx="+sidx+"&isbn="+isbn,
		success : function(result) {
			if(result == 1){
				alert("삭제되었습니다.");
				location.replace("/comic/order/cart.do");
			}
		}
	});
}