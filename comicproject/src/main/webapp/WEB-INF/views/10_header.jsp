<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="/comic/resources/js/user/user.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<c:set var="uidx" value="${uidx }" scope="session" />
<c:set var="id" value="${id }" scope="session" />
<c:set var="name" value="${name }" scope="session" />
<c:set var="point" value="${point }" scope="session" />
<c:set var="type" value="${type }" scope="session" />


<body>

<div class="smart-scroll">
	<!-- *** TOPBAR *** -->
	<div id="top">
		<div class="container">
			<div class="col-md-6 offer" data-animate="fadeInDown">
				<span style="color: white;">현재는 봉천동 일부 지역만 서비스 가능합니다.</span>
			</div>


			<div class="col-md-6" data-animate="fadeInDown">
				<ul class="menu">
					<c:choose>
						<c:when test="${fn:contains(isyn,'Y') and type eq 'C'}">
							<li><span style="color: white;">${name}(${id})님
									환영홥니다.</span></li>
							<li><span style="color: white;">현재 잔여 포인트 : ${point}
							</span></li>
							<li><a href="/comic/myInfo.do">마이페이지</a></li>
							<li><a href="/comic/signout.do">로그아웃</a></li>
						</c:when>
						<c:when test="${isyn eq 'Y' and type eq 'S'}">
							<div class="float-right btn-wrapper" style="margin-top: 40px;">
								<span style="color: blue" class="navlogininfo">${name}(${id})님
									환영합니다. || </span> <span style="color: blue" class="navlogininfo">현재
									잔여 포인트 : ${point } || </span> <a href="/comic/myInfo.do"><span
									style="color: blue" class="navlogininfo">마이페이지 || </span></a> <a
									href="/comic/store/listbook.do"><span style="color: blue"
									class="navlogininfo">도서관리 || </span></a> <a
									href="/comic/signout.do"><span style="color: blue">로그아웃</span></a>
							</div>
						</c:when>
						<c:otherwise>
							<li><a href="#" data-toggle="modal"
								data-target="#login-modal">로그인</a></li>
							<li><a href="#" data-toggle="modal" data-target="#reg-modal">회원가입</a>
							</li>

						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>


		<!-- login modal -->
		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
			aria-labelledby="Login" aria-hidden="true">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Login">로그인</h4>

					</div>
					<div class="modal-body">
						<form action="/comic/signin.do" method="post" name="signin"
							onsubmit="return checkLogin()">
							<div class="form-group">
								<input type="email" class="form-control" name="id"
									id="email-modal" placeholder="id@email" required> <span
									id="checkEmail"></span>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name="pwd"
									id="password-modal" placeholder="password" required>
							</div>

							<p class="text-center">

								<button type="submit" class="btn btn-primary">
									<i class="fa fa-sign-in"></i> Log in
								</button>
									<br><a id="kakao-login-btn"
									href="https://kauth.kakao.com/oauth/authorize?client_id=118237743806f276d679025f706c0e3c&&redirect_uri=http://localhost:8080/comic/kakaologin.do&&response_type=code">
									카카오톡 로그인</a> <br> <a
									href="https://kauth.kakao.com/oauth/logout?client_id=118237743806f276d679025f706c0e3c&&&logout_redirect_uri=http://localhost:8080/comic/index.do">logout</a>
								
							</p>

						</form>

						<p class="text-center text-muted">
							<a href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#reg-modal"><strong>회원가입</strong></a> <br> <a
								href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#findid-modal">아이디찾기</a> <br> <a href="#"
								data-dismiss="modal" data-toggle="modal"
								data-target="#findpwd-modal">비밀번호 찾기</a>
						</p>

					</div>
				</div>
			</div>
		</div>

		<!-- register modal -->
		<div class="modal fade" id="reg-modal" tabindex="-1" role="dialog"
			aria-labelledby="Login" aria-hidden="true">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Login">회원가입</h4>

					</div>
					<div class="modal-body">


						<form action="/comic/signup.do" method="post" name="signup"
							onsubmit="return checkValue()">
							<div class="form-group">
								<span>아이디</span> <input type="email" class="form-control"
									name="id" id="id" placeholder="E-Mail 형식 (ex: abc@mail.com)"
									onblur="checkId()" required>
								<p class="text-center" style="margin-top: 16px;">
									<span id="checkEmailup"></span>
								</p>
							</div>

							<div class="form-group">
								<span>비밀번호(3개이상 연속숫자 사용 불가 )</span> <input type="password"
									class="form-control" name="pwd" id="pwd"
									placeholder="8자리이상(특수문자포함필수)" required>
							</div>
							<div class="form-group">
								<span>비밀번호 확인</span> <input type="password" class="form-control"
									id="pwdcheck" placeholder="비밀번호 재입력" required autofocus>
							</div>
							<div class="form-group">
								<span>이 름</span> <input type="text" class="form-control"
									name="name" id="name" placeholder="이 름 입력" required>
							</div>


							<input type="hidden" name="addr" id="addr" value="" />

							<div class="form-group">
								<span>주 소</span> <input type="text" class="form-control"
									id="addrf" placeholder="검색 버튼 클릭" readonly>
								<p class="text-center" style="margin-top: 16px;">
									<input type="button" onclick="openApiAddr('nomal')" value="검색"
										class="btn btn-primary">
								</p>
							</div>



							<div class="form-group">
								<span>상세 주소</span> <input type="text" class="form-control"
									name="addrd" id="addrd" placeholder="나머지 주소 입력" required>
							</div>



							<div class="form-group">
								<span>연락처</span> <input type="tel" class="form-control"
									name="phone" id="phone" maxlength="12"
									placeholder="핸드폰 번호 (ex: 01039063915)" onchange="addrAppend()"
									required>
							</div>
							<div class="form-group">
								<span>생년월일</span> <input type="date" class="form-control"
									name="birth" id="birth" required>
							</div>




							<p class="text-center">
								<button class="btn btn-primary" type="submit">
									<i class="fa fa-sign-in"></i> 회원가입
								</button>
							</p>


						</form>

					</div>
				</div>
			</div>
		</div>
		<!-- findId modal -->
		<div class="modal fade" id="findid-modal" tabindex="-1" role="dialog"
			aria-labelledby="findid" aria-hidden="true">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Login">ID 찾기</h4>

					</div>
					<div class="modal-body">
						<form action="#" method="post" name="findIdAjax">
							<div class="form-group">
								<span>이름</span> <input type="text" class="form-control"
									name="name" id="findIdname" placeholder="이름 입력" required>
							</div>
							<div class="form-group">
								<span>생년월일</span> <input type="date" class="form-control"
									name="birth" id="findIdbirth" placeholder="생년월일 입력" required>
							</div>

							<p class="text-center">
								<span id="checkFindMes"></span><br>
								<button type="button" class="btn btn-primary" id='findIdbutton'
									onclick="FindIdAjax();" style="margin-top: 5px;">
									<i class="fa fa-sign-in"></i> 아이디 찾기
								</button>
							</p>
						</form>

						<p class="text-center text-muted">
							<a href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#reg-modal"><strong>회원가입</strong></a> <br> <a
								href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#login-modal">로그인</a> <br> <a href="#"
								data-dismiss="modal" data-toggle="modal"
								data-target="#findpwd-modal">비밀번호 찾기</a>
						</p>

					</div>
				</div>
			</div>
		</div>

		<!-- findPwd modal -->
		<div class="modal fade" id="findpwd-modal" tabindex="-1" role="dialog"
			aria-labelledby="findid" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Login">비밀번호 찾기</h4>

					</div>
					<div class="modal-body">


						<form action="/comic/findPwd.do" method="post" name="findIdAjax">
							<div class="form-group">
								<span>이름</span> <input type="text" class="form-control"
									name="name" id="findPwdname" placeholder="이름 입력" required>
							</div>
							<div class="form-group">
								<span>핸드폰 번호</span> <input type="tel" class="form-control"
									name="phone" maxlength="12" id="findPwdtel"
									onKeyDown="onlyNumberInput2(event)" placeholder="가입 시 등록한 번호"
									required />

							</div>
							<p class="text-center">
								<span id="checkFindMes"></span><br>
								<button type="submit" class="btn btn-primary" id='findPwdbutton'
									onclick="FindPwdAjax();" style="margin-top: 5px;">
									<i class="fa fa-sign-in"></i> 비밀번호 찾기
								</button>
							</p>
						</form>

						<p class="text-center text-muted">
							<a href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#reg-modal"><strong>회원가입</strong></a> <br> <a
								href="#" data-dismiss="modal" data-toggle="modal"
								data-target="#login-modal">로그인</a> <br> <a href="#"
								data-dismiss="modal" data-toggle="modal"
								data-target="#findid-modal">아이디 찾기</a>
						</p>

					</div>
				</div>
			</div>
		</div>



	</div>

	<!-- *** TOP BAR END *** -->

	<!-- *** NAVBAR *** -->

	<div class="navbar navbar-default yamm" role="navigation" id="navbar">
		<div class="container">
			<div class="navbar-header">

				<a class="navbar-brand home" href="/comic/index.do"
					data-animate-hover="bounce"> <img
					src="/comic/resources/img/logo.png" alt="Obaju logo"
					class="hidden-xs"> <img
					src="/comic/resources/img/logo-small.png" alt="Obaju logo"
					class="visible-xs"><span class="sr-only">방빠닥 코믹스</span>
				</a>
				<div class="navbar-buttons">

					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navigation">
						<span class="sr-only">Toggle navigation</span> <i
							class="fa fa-align-justify"></i>
					</button>

					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#search">
						<span class="sr-only">Toggle search</span> <i class="fa fa-search"></i>
					</button>

					<a class="btn btn-default navbar-toggle" href="/comic/pay/cart.do">
						<i class="fa fa-shopping-cart"></i> <span class="hidden-xs">최근
							확인 도서</span>
					</a>
				</div>
			</div>
			<!--/.navbar-header -->

			<div class="navbar-collapse collapse" id="navigation">

				<ul class="nav navbar-nav navbar-left">
					<li class="active"><a href="index.html">Home</a></li>

					<li class="dropdown yamm-fw"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-delay="200">STORE <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<div class="yamm-content">
									<div class="row">
										<div class="col-sm-3">
											<h5>지역별</h5>
											<ul>
												<li><a href="category.html">T-shirts</a></li>
												<li><a href="category.html">Shirts</a></li>
												<li><a href="category.html">Pants</a></li>
												<li><a href="category.html">Accessories</a></li>
											</ul>
										</div>
										<div class="col-sm-3">
											<h5>소장 도서별</h5>
											<ul>
												<li><a href="category.html">Trainers</a></li>
												<li><a href="category.html">Sandals</a></li>
												<li><a href="category.html">Hiking shoes</a></li>
												<li><a href="category.html">Casual</a></li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
			
					<li class="dropdown yamm-fw"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-delay="200">BOOK <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<div class="yamm-content">
									<div class="row">
										<div class="col-sm-3">
											<h5>지역별</h5>
											<ul>
												<li><a href="category.html">T-shirts</a></li>
												<li><a href="category.html">Shirts</a></li>
												<li><a href="category.html">Pants</a></li>
												<li><a href="category.html">Accessories</a></li>
											</ul>
										</div>
										<div class="col-sm-3">
											<h5>소장 도서별</h5>
											<ul>
												<li><a href="category.html">Trainers</a></li>
												<li><a href="category.html">Sandals</a></li>
												<li><a href="category.html">Hiking shoes</a></li>
												<li><a href="category.html">Casual</a></li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul></li>

					<li class="dropdown yamm-fw"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-delay="200">NOTICEBOARD <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li>
								<div class="yamm-content">
									<div class="row">
										<div class="col-sm-3">
											<a href="/comic/noticeList.do"><h5>게시판</h5></a>
											<ul>
												<li><a href="category.html">T-shirts</a></li>
												<li><a href="category.html">Shirts</a></li>
											</ul>
										</div>
										<div class="col-sm-3">
											<h5>공지사항</h5>
											<ul>
												<li><a href="category.html">Trainers</a></li>
												<li><a href="category.html">Sandals</a></li>
											</ul>
										</div>
										<div class="col-sm-3">
											<h5>이벤트</h5>
											<ul>
												<li><a href="category.html">Trainers</a></li>
												<li><a href="category.html">Sandals</a></li>
												<li><a href="category.html">Hiking shoes</a></li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul></li>

					<li class="dropdown yamm-fw"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-delay="200">CUSTOMER SERVICE<b
							class="caret"></b></a>

						<ul class="dropdown-menu">
							<li>
								<div class="yamm-content">
									<div class="row">
										<div class="col-sm-3">
											<h5>리뷰 게시판</h5>
											<ul>
												<li><a href="index.html">리뷰 게시판</a></li>
												<li><a href="category.html">Category - sidebar left</a>
												</li>
												<li><a href="category-right.html">Category -
														sidebar right</a></li>
												<li><a href="category-full.html">Category - full
														width</a></li>
												<li><a href="detail.html">Product detail</a></li>
											</ul>
										</div>

										<div class="col-sm-3">
											<h5>User</h5>
											<ul>
												<li><a href="register.html">Register / login</a></li>
												<li><a href="customer-orders.html">Orders history</a></li>
												<li><a href="customer-order.html">Order history
														detail</a></li>
												<li><a href="customer-wishlist.html">Wishlist</a></li>
												<li><a href="customer-account.html">Customer
														account / change password</a></li>
											</ul>
										</div>
										<div class="col-sm-3">
											<h5>Order process</h5>
											<ul>
												<li><a href="basket.html">Shopping cart</a></li>
												<li><a href="checkout1.html">Checkout - step 1</a></li>
												<li><a href="checkout2.html">Checkout - step 2</a></li>
												<li><a href="checkout3.html">Checkout - step 3</a></li>
												<li><a href="checkout4.html">Checkout - step 4</a></li>
											</ul>
										</div>
										<div class="col-sm-3">
											<h5>Pages and blog</h5>
											<ul>
												<li><a href="blog.html">Blog listing</a></li>
												<li><a href="post.html">Blog Post</a></li>
												<li><a href="faq.html">FAQ</a></li>
												<li><a href="text.html">Text page</a></li>
												<li><a href="text-right.html">Text page - right
														sidebar</a></li>
												<li><a href="404.html">404 page</a></li>
												<li><a href="contact.html">Contact</a></li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul></li>
				</ul>

			</div>
			<!--/.nav-collapse -->

			<div class="navbar-buttons">

				<div class="navbar-collapse collapse right" id="basket-overview">
					<a href="/comic/pay/cart.do" class="btn btn-primary navbar-btn"><i
						class="fa fa-shopping-cart"></i><span class="hidden-sm">최근
							확인 도서</span></a>
				</div>
				<!--/.nav-collapse -->

				<div class="navbar-collapse collapse right" id="search-not-mobile">

					<button type="button" class="btn navbar-btn btn-primary"
						data-toggle="collapse" data-target="#search">
						<span class="sr-only">Toggle search</span> <i class="fa fa-search"></i>
						<span class="hidden-sm">도서 검색</span>
					</button>
				</div>

			</div>

			<div class="collapse clearfix" id="search">

				<form class="navbar-form" role="search" action="/comic/search.do">

					<select name="target" class="form-control"
						style="margin-bottom: 10px;">
						<option selected="selected" value="title"
							style="margin-bottom: 10px;">책 제목</option>
						<option value="person">저자</option>
						<option value="publisher">출판사</option>
					</select>
					<div class="input-group">
						<input type="text" class="form-control" placeholder="찾는 책이 있나요?"
							name="query" required> <span class="input-group-btn">

							<button type="submit" class="btn btn-primary">
								<i class="fa fa-search"></i>
							</button>

						</span>
					</div>
				</form>

			</div>
			<!--/.nav-collapse -->

		</div>
		<!-- /.container -->
	</div>
	<!-- /#navbar -->

	<!-- *** NAVBAR END *** -->
	
	</div>
	
	<script type="text/javascript">
	/*date check max today*/
	var today = new Date();
	var yyyy = today.getFullYear();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '-' + mm + '-' + dd;
	document.getElementById('birth').setAttribute("max", today);
	document.getElementById('findIdbirth').setAttribute("max", today);
	</script>