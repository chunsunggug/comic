function addCart(pk){
	
	$.ajax({
		url : "/comic/additemtocart.do?pk="+pk,
		success : function(result) {
			if(result == 1){
				if(confirm("추가되었습니다. 카트로 이동하시겠습니까?")){
					location.href = "/comic/pay/cart.do";
				}
			}
		}
	});
		
}