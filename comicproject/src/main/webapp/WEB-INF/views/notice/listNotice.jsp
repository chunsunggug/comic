<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<main id="all">

	<div id="content">
		<div class="container">

			<div class="col-md-12">

				<ul class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li>게시판</li>
				</ul>

			</div>

			<div class="col-md-12" id="customer-orders">
				<div class="box">
					<h1>게시판</h1>
					<p class="lead">다양한 이야기를 나누어 보아요 =)</p>
					<%-- <c:if test="${empty sid}">
						<p class="text-muted">게시글 작성을 위해 회원가입 후 로그인이 필요합니다.</p>
					</c:if> --%>
					<hr>

					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th style="text-align: center;">No</th>
									<th style="text-align: center;">카테고리</th>
									<th style="text-align: center;">제목</th>
									<th style="text-align: center;">작성자</th>
									<th style="text-align: center;">작성일</th>
								</tr>
							</thead>
							<%-- <tbody>
								<c:if test="${empty list}">
									<tr>
										<td colspan="5">등록된 게시글이 없습니다.</td>
									</tr>
								</c:if>
								<c:forEach var="ndto" items="${list }">
									<tr>
										<td style="text-align: center;">${ndto.cidx }</td>
										<c:url var="contentUrl" value="bbsDetail.do">
											<c:param name="cidx">${ndto.cidx }</c:param>
										</c:url>
										<c:if test="${ndto.category eq '공지' }">
											<td
												style="text-align: center; color: red; font-weight: bolder;">${ndto.category }</td>
											<td width="500"><a href="${contentUrl }"
												style="color: red; font-weight: bolder;">${dto.title }</a></td>
										</c:if>
										<c:if test="${dto.category eq '이벤트' }">
											<td
												style="text-align: center; color: blue; font-weight: bolder;">${dto.category }</td>
											<td width="500"><a href="${contentUrl }"
												style="color: blue; font-weight: bolder;">${dto.title }</a></td>
										</c:if>
										<c:if test="${dto.category eq '일상' }">
											<td style="text-align: center; color: black;">${dto.category }</td>
											<td width="500"><a href="${contentUrl }"
												style="color: black;">${dto.title }</a></td>
										</c:if>
										<td style="text-align: center;">${dto.writer }</td>
										<td style="text-align: center;">${dto.writedate }</td>
									</tr>
								</c:forEach>
							</tbody> --%>
						</table>
						<div class="col-sm-12 text-center">
						<button type="button" class="btn btn-primary"
									onclick="location.href='addNoticeForm.do'">글쓰기</button>
							<%-- <c:if test="${!empty sname }">
								<button type="button" class="btn btn-primary"
									onclick="location.href='bbsWrite.do'">글쓰기</button>
							</c:if> --%>
						</div>
						<div class="pages">12345
							<%-- <ul class="pagination">${pageStr } --%>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /.container -->
	</div>
	<!-- /#content -->
<!-- /#all -->