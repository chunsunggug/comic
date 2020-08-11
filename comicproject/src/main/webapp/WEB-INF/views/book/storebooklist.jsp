<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<section class="pt-md-10 sec-pb-70 pt-8 pb-6 bg-light">
	<div class="container">

		<div class="container">
			<div class="col-md-12">
				<ul class="breadcrumb">
					<li><a href="/comic/index.do">Book</a></li>
					<li>Book Detail</li>
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
				<div id="content" class="row products">
					<c:forEach var="item" items="${items}">
					<div class="col-md-3 col-sm-3 col-xs-6">
						<div class="product">
							<div>
								<a href="${item.url}"> 
								<c:choose>
									<c:when test="${item.thumbnail == null}">
									<img src="/comic/resources/img/book/unknown_cover.png"
									alt="" class="img-responsive" style="margin:auto;">
									</c:when>
									<c:otherwise>
									<img src="${item.thumbnail}"
									alt="" class="img-responsive" style="margin:auto;">
									</c:otherwise>
								</c:choose>
								</a>
							</div>

							<div class="text">
								<h3>
									<a href="${item.url}">${item.title }</a>
								</h3>
								<p class="price">${item.point}</p>
								<p class="text-center">
									<c:choose>
										<c:when test="${item.status == 'S' }"> 대여가능 </c:when>
										<c:otherwise> 대여 불가 </c:otherwise>
									</c:choose>
								</p>
								<p class="buttons">
									<a href="${item.url}" class="btn btn-default">자세히</a> <a
										href='javascript:addCart("${item.sbidx}")' class="btn btn-primary"><i
										class="fa fa-shopping-cart"></i>장바구니 담기</a>
								</p>
							</div>
							<!-- /.text -->
						</div>
						<!-- /.product -->
					</div>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
</section>




<script src="/comic/resources/js/pay/cart.js?v=<%=System.currentTimeMillis() %>"></script>