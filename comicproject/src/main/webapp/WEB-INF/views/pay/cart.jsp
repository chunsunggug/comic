<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="org.json.simple.parser.JSONParser"%>

<section class="pt-md-10 sec-pb-70 pt-8 pb-6 bg-light">
	<div id="content">

		<div class="container">
			<div class="col-md-12">
				<ul class="breadcrumb">
					<li><a href="/comic/index.do">Home</a></li>
					<li>Cart</li>
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
			
			<div class="col-md-9" id="basket">

				<div class="box">

					<form method="post" action="/comic/order/pay.do">

						<h1>Shopping cart</h1>
						<p class="text-muted">You currently have ${tot_count} item(s) in your
							cart.</p>
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th colspan="2">도서</th>
										<th>저자/번역</th>
										<th>상태</th>
										<th colspan="2">대여료</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="tot" value="0" />
									<c:forEach var="book" items='${cartlist}'>
									<tr>
										<td><a href="/comic/bookdetail.do?isbn=${book.isbn13}&sidx=${book.sidx}">
												<img src="${book.thumbnail }"/>
										</td>
										<td><a href="/comic/bookdetail.do?isbn=${book.isbn13}&sidx=${book.sidx}">${book.title }</a></td>
										<td>
											<c:if test="${book.authors.size() > 0 }">
												<c:forEach var="i" begin="0" end="${book.authors.size() - 1}">
													${book.authors[i]}
													<c:if test="${ i != book.authors.size() - 1 }">
													,
													</c:if>
												</c:forEach>
											</c:if>
											<c:if test="${book.translators != null }">
											<br>
											</c:if>
											<c:if test="${book.translators.size() > 0 }">
												<c:forEach var="i" begin="0" end="${book.translators.size() - 1}">
													${book.translators[i]}
												<c:if test="${ i != book.translators.size() - 1 }">
												,
												</c:if>
												</c:forEach>
											</c:if>
										</td>
										<td>
											<c:choose>
											<c:when test="${book.status == 'S'}">
												<span class="label label-info">대여가능</span>
											</c:when>
											<c:otherwise>
												<span class="label label-danger">대여불가능</span>
											</c:otherwise>
											</c:choose>
										</td>
										<td>${bookmark.point }</td>
										<td><a href="javascript:deleteCartItem('${book.sidx}',${book.isbn13})"><i class="fa fa-trash-o"></i></a></td>
									</tr>
									<c:set var="tot" value="${tot+book.point}" />
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th colspan="4">합계</th>
										<th colspan="2">${tot != null ? tot : 0}</th>
									</tr>
								</tfoot>
							</table>

						</div>
						<!-- /.table-responsive -->

						<div class="box-footer">
							<div class="pull-left">
								<a href="/comic" class="btn btn-default"><i
									class="fa fa-chevron-left"></i> 다른 책 보러가기</a>
							</div>
							<div class="pull-right">
								<button class="btn btn-default">
									<i class="fa fa-refresh"></i> 새로고침
								</button>
								<button type="submit" class="btn btn-primary">
									결제 진행하기 <i class="fa fa-chevron-right"></i>
								</button>
							</div>
						</div>

					</form>

				</div>
				<!-- /.box -->
			</div>
			<!-- /.col-md-9 -->
			
		</div>
	</div>
</section>
<script
	src="/comic/resources/js/pay/cart.js?v=<%=System.currentTimeMillis() %>"></script>




