<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
	<div class="container">
		<div class="row text-center align-items-center justify-content-center"
			style="height: 300px;">
			<!-- Search Box -->
			<div class="search-box-2">
				<form class="form-row justify-content-center" method="GET"
					action="/comic/search.do">

					<div class="form-group col-md-5 col-lg-4">
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<select class="input-group-text" name="sort">
									<option selected="selected" value="accuracy">정확도순</option>
									<option value="recency">최신순</option>
								</select>
								</div>
								<div class="input-group-prepend">
								<select class="input-group-text" name="target">
									<option selected="selected" value="title">책 제목</option>
									<option value="person">저자</option>
									<option value="publisher">출판사</option>
									<option value="isbn">ISBN</option>
								</select>
							</div>
							<input type="text" class="form-control" name="query" placeholder="찾는 책이 있나요?" required>
						</div>
					</div>


					<div class="form-group col-md-3 col-lg-2">
						<button type="submit" class="btn btn-block btn-primary">
							검색 <i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>

	<div class="container">
		<div id="contents_container" class="row">

			<c:forEach var="content" items="${documents}">
				<div class="col-xs-6 col-sm-3 col-md-2" style="height:220px;" >
					<a href="https://www.naver.com"> <c:choose>
							<c:when test="${content.thumbnail == ''}">
								<img class="img-thumbnail img-responsive"
									src="/comic/resources/img/book/unknown_cover.png" />
							</c:when>
							<c:otherwise>
								<img class="img-thumbnail img-responsive"
									src="${content.thumbnail}" />
							</c:otherwise>
						</c:choose>
						<br>
						<h5 style="overflow:auto; text-overflow:ellipsis;">${content.title}</h5>
					</a>
				</div>
			</c:forEach>
		</div>
		<c:if test="${!meta.is_end}">
		<div class="container text-center">
			<button class="btn btn-primary" id="btn_more_load" style="margin-bottom: 2em;"
				onclick="show_contents()">더 보기</button>
		</div>
		</c:if>
	</div>


</section>
</body>

<script src="/comic/resources/js/search/booksearch.js?v=<%=System.currentTimeMillis() %>" ></script>