<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.parser.JSONParser" %>

<section class="pt-md-10 sec-pb-70 pt-8 pb-6 bg-light">
	<div class="container">

		<div class="container">
			<div class="col-md-12">
				<ul class="breadcrumb">
					<li><a href="/comic/index.do">Book</a></li>
					<li>Book Mark</li>
				</ul>
			</div>

			<div class="col-md-3">
				<!-- *** CUSTOMER MENU *** -->
				<div class="panel panel-default sidebar-menu">
					<div class="panel-heading">
						<h3 class="panel-title">Book Menu</h3>
					</div>
					<div class="panel-body">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="/comic/myInfo.do"><i
									class="fa fa-list"></i>최신간</a></li>
							<li><a href="#"><i class="fa fa-heart"></i>인기순</a></li>
							<li><a href="#"><i class="fa fa-user"></i>카테고리</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- *** CUSTOMER MENU END *** -->

			<div class="col-md-9">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>마크</th>
							<th>표지</th>
							<th>제목</th>
							<th>저자/번역</th>
							<th>상태</th>
							<th>대여료</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bookmark" items='${cart}'>
							<tr>
								<th>마크</th>
								<th><img src="${bookmark.thumbnail}" style="width:auto;height:auto;"/></th>
								<td>${bookmark.title}</td>
								<td>
									<c:if test="${bookmark.authors.size() > 0 }">
										<c:forEach var="i" begin="0" end="${bookmark.authors.size() - 1}">
												${bookmark.authors[i]}
											<c:if test="${ i != bookmark.authors.size() - 1 }">
											,
											</c:if>
										</c:forEach>
									</c:if> 
									<c:if test="${bookmark.translators != null }">
									<br>
									</c:if> 
									<c:if test="${bookmark.translators.size() > 0 }">
										<c:forEach var="i" begin="0" end="${bookmark.translators.size() - 1}">
											${bookmark.translators[i]}
											<c:if test="${ i != bookmark.translators.size() - 1 }">
											,
											</c:if>
										</c:forEach>
									</c:if>
								</td>
								<td><c:choose>
										<c:when test="${bookmark.status == 'S'}">
											<span class="label label-info">대여가능</span>
										</c:when>
										<c:otherwise>
											<span class="label label-danger">대여불가능</span>
										</c:otherwise>
									</c:choose></td>
								<td>${bookmark.point }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</section>
<script src="/comic/resources/js/pay/bookmark.js?v=<%=System.currentTimeMillis() %>"></script>




