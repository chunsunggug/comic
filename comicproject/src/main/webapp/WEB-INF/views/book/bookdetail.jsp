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

				<div class="row" id="productMain">
					<div class="col-sm-6">
						<div id="mainImage">
							<img src="${item.thumbnail}" style="margin:auto; width:75%;" class="img-responsive">
						</div>
						
					</div>
					<div class="col-sm-6">
						<div class="box">
							<h1 class="text-center">${item.title}</h1>
							<p class="goToDescription">
								<a href="#details" class="scroll-to">자세히로</a>
							</p>
							<p class="price">${item.point}</p>
							<p class="text-center buttons">
							<h5></h5>
								<a href='javascript:addCart("${item.sbidx}")' class="btn btn-primary"><i
									class="fa fa-shopping-cart"></i> 장바구니 담기</a> <a
									href="#" class="btn btn-default"><i
									class="fa fa-heart"></i> 찜하기 </a>
							</p>

						</div>
 					
					</div>
				</div>


				<div class="box" id="details">
					<p>
					<h4>저자</h4>
					<ul>
						<c:forEach var="auth" items="${item.authors}">
							<li>${auth} </li>
						</c:forEach>
					</ul>
					<h4>도서 소개</h4>
					<p>${item.contents}</p>
					
					<hr>
					<div class="social">
						<h4>Show it to your friends</h4>
						<p>
							<a href="#" class="external facebook" data-animate-hover="pulse"><i
								class="fa fa-facebook"></i></a> <a href="#" class="external gplus"
								data-animate-hover="pulse"><i class="fa fa-google-plus"></i></a>
							<a href="#" class="external twitter" data-animate-hover="pulse"><i
								class="fa fa-twitter"></i></a> <a href="#" class="email"
								data-animate-hover="pulse"><i class="fa fa-envelope"></i></a>
						</p>
					</div>
				</div>

			</div>

		</div>
	</div>
</section>




<script src="/comic/resources/js/pay/cart.js?v=<%=System.currentTimeMillis() %>"></script>