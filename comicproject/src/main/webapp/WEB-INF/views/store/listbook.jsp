<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section>
	<div style="height: 200px;margin-bottom: 10px"></div>
	<div class="container-fluid text-right">
		<button class="btn btn-primary" data-toggle="modal" type="button"
			data-target="#add-book-modal">도서추가</button>
			
		<button class="btn btn-primary" data-toggle="modal" type="button"
			data-target="#updel-book-modal">도서수정</button>
			
	</div>
	<div class="container-fluid text-center">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>번호</th>
					<th>이미지</th>
					<th>도서명</th>
					<th>저자</th>
					<th>장르</th>
					<th>포인트</th>
					<th>등록일</th>
					<th>상태변경</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${listitem}">
				<tr>
					<th style="vertical-align:middle">${item.sbidx}</th>
					<th style="vertical-align:middle"><img class="img-responsive" src="${item.image}" /></th>
					<th style="vertical-align:middle">${item.title}</th>
					<th style="vertical-align:middle">${item.author}</th>
					<th style="vertical-align:middle">${item.genre}</th>
					<th style="vertical-align:middle">${item.point}</th>
					<th style="vertical-align:middle">${item.sdate}</th>
					<th style="vertical-align:middle">${item.status}</th>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="container-fluid">
		<ul class="pagination">
			${pagestr}
    </ul>
	</div>

	<!-- 도서추가 모달 -->
	<div class="modal fade" id="add-book-modal" tabindex="-1" role="dialog"
		aria-labelledby="add-book-modal-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">도서 추가하기</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div id="add-left" class="col-4">
								<img id="add-img" class="img-responsive"
									src="/comic/resources/img/book/unknown_cover.png" />
							</div>
							<div id="add-right" class="col-8">
								<div class="row">
									<ul class="list-unstyled col-12">
										<li><input id="add-title" class="col-12" type="text"
											disabled="disabled" placeholder="책 제목" /></li>
										<li><input id="add-authors" class="col-12" type="text"
											disabled="disabled" placeholder="저자" /></li>
										<li><input id="add-publisher" class="col-12" type="text"
											disabled="disabled" placeholder="출판사" /></li>
										<li><input id="add-point" class="col-12" type="number"
											min="0" disabled="disabled" placeholder="대여료" /></li>
										<li><input id="add-tot" class="col-12" type="number"
											min="0" disabled="disabled" placeholder="총 권수" /></li>
										<li><input id="add-isbn" class="col-12" type="number"
											placeholder="ISBN13 or ISBN10" /></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" id="add-btn">등록하기</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 도사 수정/삭제 모달 -->
	<div class="modal fade" id="updel-book-modal" tabindex="-1" role="dialog"
		aria-labelledby="updel-book-modal-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">도서 수정/삭제하기</div>
				<div class="modal-body">
					<div class="container-fluid">
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="update-btn">삭제하기</button>
					<button type="button" class="btn btn-primary" id="delete-btn">수정하기</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</section>

<script src="/comic/resources/js/store/store-book.js?v=<%=System.currentTimeMillis() %>"></script>