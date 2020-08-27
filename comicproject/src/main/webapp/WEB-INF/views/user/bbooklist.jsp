<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-12">
			<div class="container-fluid text-right">
				<button id="rreqbtn" class="btn btn-primary" type="button">반납신청</button>
			</div>
			<div class="container-fluid text-center">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" class="allcb" /></th>
							<th>번호</th>
							<th>이미지</th>
							<th>도서명</th>
							<th>포인트</th>
							<th>신청일</th>
							<th>도착일</th>
							<th>반납예정일</th>
							<th>연체</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${items}">
							<tr>
								<th style="vertical-align: middle"><input type="checkbox"
								class="cb" value="${item.oaidx}" /></th>
								<th style="vertical-align: middle">${item.oaidx}</th>
								<th style="vertical-align: middle"><img
									class="img-responsive" src="${item.thumbnail}" /></th>
								<th style="vertical-align: middle">${item.title}</th>
								<th style="vertical-align: middle">${item.point}</th>
								<th style="vertical-align: middle">${item.breqdate}</th>
								<th style="vertical-align: middle">${item.bdcdate}</th>
								<th style="vertical-align: middle">${item.expdate}</th>
								<th style="vertical-align: middle">${item.deldate}</th>
								<th style="vertical-align: middle">
									<c:choose>
										<c:when test="${item.state == 'BREQ' }"> 요청확인중
										</c:when>
										<c:when test="${item.state == 'BC' }"> 배송대기중
										</c:when>
										<c:when test="${item.state == 'BD' }"> 배송중
										</c:when>
										<c:when test="${item.state == 'BDC' }"> <button class="delreqbtn btn-danger btn" onclick="delayreq(${item.oaidx})">연장신청</button>
										</c:when>
									</c:choose>
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="container-fluid" style="text-align: center;">
				<ul class="pagination">${pagestr}
				</ul>
			</div>
		</div>
		
<script>
function delayreq(value){
	var oaidx = value;

	$.ajax({
		type : 'post',
		url : "http://localhost:8080/comic/delayreq.do?oaidx="+oaidx,
		success : function(result){
			if( result != 0 ){
				alert("연장되었습니다");
				window.location.replace("http://localhost:8080/comic/bbooklist.do");
			}else{
				alert("더이상 연장할 수 없습니다");
			}
		}
	});
	
};

$('#rreqbtn').click( function() {
	var items = $('.cb:checked');
	var values = new Array();
	var total = 0;
	
	items.each(function(index,item){
		values.push(item.value);
		total++;
	});
	
	if(total == 0 ) return;
	
	$.ajax({
		url : "/comic/store/breqok.do",
		type : "post",
		data : JSON.stringify(values),
		dataType : "text",
		success : function(result){
			if(result == total){
				alert("반납신청이 완료되었습니다");
				location.replace(replaceurl);
			}else{
				alert("반납신청이 실패하였습니다");
			}
		}
	});
 });
</script>