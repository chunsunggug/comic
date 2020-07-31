<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>

	<div class="container">
		<div id="contents_container" class="row">

			<c:forEach var="content" items="${documents}">
				<div class="col-xs-6 col-sm-3 col-md-2" style="height:220px; margin-bottom:5px; " >
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
						<h5 style="overflow:hidden;text-overflow:ellipsis;height:30px;">${content.title}</h5>
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