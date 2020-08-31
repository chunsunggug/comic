<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section>
	<div class="container">
		<div class="col-md-12">
			<ul class="breadcrumb">
				<li><a href="/comic/index.do">Home</a></li>
			</ul>
		</div>

		<div class="col-md-3">
			<!-- *** CUSTOMER MENU *** -->
			<div class="panel panel-default sidebar-menu">
				<div class="panel-heading">
					<h3 class="panel-title">My Page Section</h3>
				</div>
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked panel_ul">
						<li><a href="/comic/myInfo.do"><i
								class="fa fa-list"></i>내 정보</a></li>
						<li><a href="/comic/bbooklist.do"><i class="fa fa-heart"></i>대여 신청 목록</a></li>
						<li><a href="#"><i class="fa fa-user"></i>대여 완료 목록</a></li>
						<li><a href="#"><i class="fa fa-question"></i>나의 후기 작성 글</a>
						</li>
						<c:if test="${type == 'S'}">
						<li><a href="/comic/listuser.do"><i class="fa fa-heart"></i>회원관리</a></li>
						<li><a href="/comic/store/listbook.do"><i class="fa fa-heart"></i>도서관리</a></li>
						<li><a href="/comic/store/deliverymanage.do"><i class="fa fa-heart"></i>배송관리</a></li>
						</c:if>
						<li><a href="#" onclick="removeCheck()"><i
								class="fa fa-sign-out"></i>회원탈퇴</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- *** CUSTOMER MENU END *** -->

		<div class="col-md-9">
			<jsp:include page="${page}"></jsp:include>
		</div>

	</div>
</section>
<script>

$(document).ready(function(){
	var panelitem = $('.panel_ul li').eq(${panelidx});
	panelitem.addClass('active');
	$('.breadcrumb').append("<li>"+panelitem.children('a')[0].innerText+"</li>");
});
</script>