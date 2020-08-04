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
		</div>

			<div class="col-md-9">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>가게이름</th>
							<th>대여요금</th>
							<th>재고</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${items}">
						<tr>
							<th>${item.name}</th>
							<td>${item.point}</td>
							<td>
							<c:choose>
								<c:when test="${item.status == 'S'}" >
								<span class="label label-info">대여가능</span>
								</c:when>
								<c:otherwise>
								<span class="label label-danger">대여불가능</span>
								</c:otherwise>
							</c:choose>
							</td>
							<td><a href="/comic/storebooklist.do?isbn=${item.isbn}"
								class="btn btn-primary btn-sm">보러가기</a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>

		</div>
</section>




