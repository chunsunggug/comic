<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div class="container-fluid text-right">
		<select id="type">
			<option value="b" selected>대여</option>
			<option value="r">반납</option>
		</select>
		<select id="step">
			<option value="req" selected>요청</option>
			<option value="c">확인</option>
			<option value="d">배송</option>
			<option value="dc">완료</option>
		</select>
		<button class="btn btn-primary" type="button" id="view">조회</button>
		<button class="btn btn-primary" type="button" id="reqok">요청수락</button>
		<button class="btn btn-primary" type="button" id="reqdeny">요청거절</button>
	</div>
	<div class="container-fluid text-center">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><input type="checkbox" class="allcb" /></th>
					<th>주문번호</th>
					<th>요청구분</th>
					<th>이미지</th>
					<th>도서명</th>
					<th>포인트</th>
					<th>요청날짜</th>
					<th>확인날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${order_list}">
					<tr>
						<th style="vertical-align: middle"><input type="checkbox"
							class="cb" value="${item.oaidx }" /></th>
						<th style="vertical-align: middle">${item.oidx}</th>
						<th style="vertical-align: middle">${item.reqtype.equals("B") ? "대여" : "반납"}</th>
						<th style="vertical-align: middle"><img
							class="img-responsive" src="${item.thumbnail}" /></th>
						<th style="vertical-align: middle">${item.title}</th>
						<th style="vertical-align: middle">${item.point}</th>
						<th style="vertical-align: middle">${item.breqdate}</th>
						<th style="vertical-align: middle">${item.bcdate}</th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container-fluid">
		<ul class="pagination">${pagestr}
		</ul>
	</div>


