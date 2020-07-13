<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section>
	<div style="height: 200px"></div>
	<div class="container-fluid text-right">
		<button class="btn btn-primary" data-toggle="modal" type="button"
			style="margin-bottom: 10px" data-target="#add-book-modal">도서추가</button>
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
			</tbody>
		</table>
	</div>

	<!-- 도서추가 모달 -->
	<div class="modal fade" id="add-book-modal" tabindex="-1" role="dialog"
		aria-labelledby="add-book-modal-label" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">도서 추가하기</div>
				<div class="modal-body">
					<form>
						<div class="form-inline">
							<div class="form-group col-3">
								<img class="img-responsive col-12" src="/comic/resources/img/book/unknown_cover.png"
											style="padding:0px;" />
								<input type="file" class="col-12" style="padding:0px;"/>
							</div>
							<div class="form-group col-9">
								<input type="text" class="form-control col-12" id="title"
										placeholder="도서 제목 설정하기" />
								<div class="form-group col-12" style="margin-top:5px;padding:0px;">
									<label for="authors" class="col-2">저자</label>
									<input type="text" class="form-control col-4" id="authors"/> 
									<label for="publisher" class="col-2">출판사</label>
									<input type="text" class="form-control col-4" id="publisher"/>
								</div>
								<textarea class="form-control col-12" id="contents" placeholder="줄거리 작성"
								style="margin-top:5px; margin-bottom:5px; resize:none;" ></textarea>
								사용 포인트 <input type="number" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">등록하기</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</section>