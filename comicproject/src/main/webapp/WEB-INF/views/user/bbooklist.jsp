<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-12">
			<div class="container-fluid text-right">
				<button class="btn btn-primary" data-toggle="modal" type="button"
					data-target="#add-book-modal">추가</button>
				<button class="btn btn-primary" data-toggle="modal" type="button"
					data-target="#update-book-modal">수정/삭제</button>
			</div>
			<div class="container-fluid text-center">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>번호</th>
							<th>이미지</th>
							<th>도서명</th>
							<th>신청일</th>
							<th></th>
							<th>포인트</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${listitem}">
							<tr>
								<th style="vertical-align: middle">${item.sbidx}</th>
								<th style="vertical-align: middle"><img
									class="img-responsive" src="${item.thumbnail}" /></th>
								<th style="vertical-align: middle">${item.title}</th>
								<th style="vertical-align: middle"><c:forEach var="auth"
										items="${item.authors}">
					${auth} 
					</c:forEach></th>
								<th style="vertical-align: middle">${item.category}</th>
								<th style="vertical-align: middle">${item.point}</th>
								<th style="vertical-align: middle">${item.sdate}</th>
								<th style="vertical-align: middle">
									<button class="btn btn-danger"
										onclick="deleteItem(${item.sbidx})">삭제</button>
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="container-fluid">
				<ul class="pagination">${pagestr}
				</ul>
			</div>
		</div>