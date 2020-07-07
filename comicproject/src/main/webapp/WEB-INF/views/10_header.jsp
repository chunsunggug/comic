<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="id" value="${id }" scope="session" />
<c:set var="name" value="${name }" scope="session" />
<c:set var="point" value="${point }" scope="session" />
<c:set var="type" value="${type }" scope="session" />
<c:set var="isyn" value="${isyn }" scope="session" />


<body id="body" class="up-scroll">
<!-- Preloader -->
	<div id="preloader" class="smooth-loader-wrapper">
		<div class="smooth-loader">
			<div class="loader1">
				<div class="loader-target">
					<div class="loader-target-main"></div>
					<div class="loader-target-inner"></div>
				</div>
			</div>
		</div>
	</div>



<!-- login modal -->
		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
			aria-labelledby="Login" aria-hidden="true">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="Login">로그인</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<form action="signin.do" method="post" name="signin"
						onsubmit="return checkLogin()">
							<div class="form-group">
								<input type="email" class="form-control" name="id"
									id="email-modal" placeholder="id@email" required>
									<span id="checkEmail"></span>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name="pwd"
									id="password-modal" placeholder="password" required>
							</div>

							<p class="text-center">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-sign-in"></i> Log in
								</button>
							</p>

						</form>

						<p class="text-center text-muted">
							<a href="#" data-toggle="modal" data-target="#reg-modal"><strong>회원가입</strong></a>
							<br><a href="#" data-toggle="modal" data-target="#findid-modal">아이디찾기</a>
							<br><a href="#" data-toggle="modal" data-target="#findpwd-modal">비밀번호 찾기</a>
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
					<h4 class="modal-title" id="Login">회원가입</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">


					<form action="signup.do" method="post" name="signup"
						onsubmit="return checkValue()">
						<div class="form-group">
						<span>아이디</span>
							<input type="email" class="form-control" name="id" id="id"
								placeholder="E-Mail 형식 (ex: abc@mail.com)" onblur="checkId()"
								required>
							<p class="text-center" style="margin-top: 16px;">
								<span id="checkEmail"></span>
							</p>
						</div>

						<div class="form-group">
						<span>비밀번호</span>
							<input type="password" class="form-control" name="pwd" id="pwd"
								placeholder="8자리이상 입력" required>
						</div>
						<div class="form-group">
						<span>비밀번호 확인</span>
							<input type="password" class="form-control" id="pwdcheck"
								placeholder="비밀번호 재입력" required autofocus>
						</div>
						<div class="form-group">
						<span>이 름</span>
							<input type="text" class="form-control" name="name" id="name"
								placeholder="이 름 입력" required>
						</div>


						<input type="hidden" name="addr" id="addr" value="" />

						<div class="form-group">
						<span>주 소</span>
							<input type="text" class="form-control" id="addrf"
								placeholder="검색 버튼 클릭" readonly>
							<p class="text-center" style="margin-top: 16px;">
								<input type="button" onclick="openApiAddr()" value="검색"
									class="btn btn-primary">
							</p>
						</div>



						<div class="form-group">
						<span>상세 주소</span>
							<input type="text" class="form-control" name="addrd" id="addrd"
								placeholder="나머지 주소 입력" required>
						</div>



						<div class="form-group">
						<span>연락처</span>
							<input type="tel" class="form-control" name="phone" id="phone" maxlength="12"
								placeholder="핸드폰 번호 (ex: 01039063915)" onblur="addrAppend()" required>
						</div>
						<div class="form-group">
						<span>생년월일</span>
							<input type="date" class="form-control" name="birth" id="birth"
								required>
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


	<!--address api  -->
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function openApiAddr() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var addrA = ''; // 합친 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다. 신주소 주석처리
							/* if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							} */

							addr = data.jibunAddress; //지번으로 사용예정
							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("addrf").value = extraAddr;

							} else {
								document.getElementById("addrf").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('addrf').value = data.zonecode;
							document.getElementById("addrf").value = addr;
							document.getElementById("addr").value = data.zonecode
									+ " " + addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("addrd").focus();
						}
					}).open();
		}
	</script>


	<!-- address hidden value add -->
	<script>
		function addrAppend() {
			$("#phone").on("keyup", function() {
			    $(this).val($(this).val().replace(/[^0-9]/g,""));
			});
		}
	</script>

	<script>
	<!--회원가입 하단의 Ajac첫번째 파라미터 url 경로 destnation , 두번째 파라미터는 보낼 데이터 값을 입력 ,	세번째 파라미터 펑션으로 실행하는데 데이터 값을 받음  리턴 받는 이름 data 리턴 받은 data를 통해 확인-->
		function checkId() {
			var id = $("#id").val();
			var check = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
			
			if (!check.test(id)) {
				$("#checkEmail").html("EMAIL 형식이 아닙니다.").css("color", "red");
			} else {
				$.post("idCheck.do", {
					"id" : $("#id").val()
				}, function(data) {
					if (data == '0') {
						$("#checkEmail").html("사용가능한 EMAIL 입니다.").css("color",
								"green");
						$("#input_pwd").focus();
					} else {
						$("#checkEmail").html("사용불가능한 EMAIL 입니다.").css("color",
								"red");
						$("#input_email").focus();
					}
				});
			}
		}
	</script>

<!--if submit to check login -->
	<script>
		$(document).ready(function() {
			document.signin.id.value = "";
		});
		function back() {
			window.location.href = 'index.do';
		}
		
		function checkLogin() {
			var id = document.signin.id.value;
			var pwd = document.signin.pwd.value;
			var check = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
			var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
			if (!check.test(id)) {
				$("#checkEmail").html("EMAIL 형식이 아닙니다.").css("color", "red");
				$("#id").focus();
				return false;
			} else {
			if (!document.signin.id.value) {
				alert("아이디를 다시 확인하세요.");
				return false;
			}
			if (document.signin.pwd.value.length<8) {
				alert("비밀번호를 확인하세요.");
				document.signup.pwd.focus();
				return false;
			}
			}
		}
	</script>

<!--if submit to check value -->
	<script>
		$(document).ready(function() {
			document.signup.id.value = "";
		});
		function back() {
			window.location.href = 'index.do';
		}
		function checkValue() {
			var phone = document.signup.phone.value;
			var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
			var id = document.signup.id.value;
			if (!document.signup.id.value) {
				alert("아이디를 다시 확인하세요.");
				return false;
			}
			if (document.signup.pwd.value.length<8) {
				alert("비밀번호를 확인하세요.");
				document.signup.pwd.focus();
				return false;
			}
			if (document.signup.pwd.value != document
					.getElementById("pwdcheck").value) {
				alert("비밀번호를 동일하게 입력하세요.");
				document.getElementById("pwdcheck").focus();
				return false;
			}
			if (!regExp.test(phone)) {
				alert('전화 번호를 정확히 입력해주세요.');
				document.signup.phone.focus();
				return false;
			}
			document.getElementById("addr").value = document.getElementById("addr").value+ " " + document.getElementById("addrd").value;
		}
	</script>

<!-- date check max today -->
	<script>
		var today = new Date();
		var yyyy = today.getFullYear();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		today = yyyy+'-'+mm+'-'+dd;
		document.getElementById('birth').setAttribute("max", today);
	</script>






	<!-- HEADER -->
	<header class="header">
		<nav class="nav-menuzord nav-menuzord-transparent navbar-sticky" id="navbackground">
			<div class="container clearfix">
				<div id="menuzord" class="menuzord menuzord-responsive">

					<a href="index.do" class="menuzord-brand"> <svg
							class="logo-svg" version="1.1" xmlns="http://www.w3.org/2000/svg"
							width="140" height="44">
                <path class="fill-primay"
								d="M0 44V0h139.813v44H0zM137.346 2.467H2.467v39.065h134.879V2.467z" />
                <path class="fill-primay"
								d="M120.927 22.389v11.095h-4.566V22.389a371.288 371.288 0 0 0-2.086-2.888 347.047 347.047 0 0 1-2.2-3.053 386.86 386.86 0 0 0-2.201-3.053c-.7-.959-1.395-1.922-2.086-2.888h5.617l5.255 7.287 5.222-7.287h5.649c.002 0-8.604 11.882-8.604 11.882zM98.034 33.484h-4.565V15.069h-6.372v-4.562h17.244v4.562h-6.306v18.415zm-21.908 0H71.56V15.069h-6.372v-4.562h17.244v4.562h-6.306v18.415zm-17.425-1.789c-.69.623-1.511 1.116-2.463 1.477-.953.361-1.987.542-3.104.542-1.007 0-1.982-.143-2.923-.427a10.814 10.814 0 0 1-2.661-1.214h.033a9.928 9.928 0 0 1-1.577-1.215 18.73 18.73 0 0 1-.953-.952l3.416-3.151c.153.197.399.432.739.706.339.274.728.537 1.166.788.44.253.902.467 1.38.64.481.175.941.262 1.379.262.372 0 .744-.044 1.117-.131.359-.082.703-.22 1.018-.41.305-.185.564-.437.755-.739.197-.306.296-.689.296-1.149 0-.175-.06-.366-.181-.574-.12-.208-.329-.432-.624-.673-.296-.241-.706-.498-1.232-.771a20.567 20.567 0 0 0-1.971-.87 25.42 25.42 0 0 1-2.562-1.132 8.896 8.896 0 0 1-2.053-1.428 5.903 5.903 0 0 1-1.347-1.871c-.317-.7-.476-1.51-.476-2.429 0-.94.175-1.822.526-2.642a6.21 6.21 0 0 1 1.494-2.133c.646-.602 1.423-1.072 2.332-1.412.908-.339 1.911-.509 3.006-.509.591 0 1.22.077 1.889.23.668.153 1.319.35 1.954.591a12.95 12.95 0 0 1 1.79.837c.558.317 1.023.64 1.396.968l-2.825 3.545a15.71 15.71 0 0 0-1.281-.788 10.316 10.316 0 0 0-1.281-.558 4.311 4.311 0 0 0-1.478-.263c-.919 0-1.637.181-2.151.542-.515.361-.772.881-.772 1.559 0 .307.093.586.279.837.186.252.438.482.756.689.348.225.717.417 1.1.574.416.176.854.34 1.314.492 1.314.504 2.42 1.013 3.318 1.526.898.514 1.62 1.062 2.168 1.642s.936 1.204 1.166 1.871c.23.668.345 1.395.345 2.183 0 .963-.197 1.871-.591 2.724a6.803 6.803 0 0 1-1.626 2.216zM34.839 10.507h4.532v22.977h-4.532V10.507zm-20.036 0h4.566v18.415h9.263v4.563H14.803V10.507z" />
              </svg>
					</a>
					<div class="float-right btn-wrapper">
 					
 					
 					<c:choose>
					<c:when test="${isyn eq 'T'.charAt(0) and type eq 'C'.charAt(0)}">
					<span style="color: blue">${name}(${id})님 환영합니다.</span><br>  
					<span style="color: blue">현재 잔여 포인트 : ${point }</span>
					<a href="signout.do"><span style="color: blue">로그아웃</span></a>
					</c:when>
					<c:otherwise>
					<a class="btn btn-outline-primary" href="#" data-toggle="modal" data-target="#login-modal" style="margin-left:5px;"><span
							id="sign">로그인</span> </a> 
							<a class="btn btn-outline-primary" href="#" data-toggle="modal" data-target="#reg-modal" style="margin: 0 15px;"><span
							id="signup">회원가입</span> </a>
					</c:otherwise> 
					</c:choose>
					</div> 					
 					
 					
 					
						
					<ul class="menuzord-menu menuzord-right">
						<li class="active"><a href="javascript:0">Home</a>
							<ul class="dropdown">
								<li><a href="index.html">Home Map</a></li>
								<li><a href="index-2.html">Home Travel</a></li>
								<li><a href="index-3.html">Home City</a></li>
								<li><a href="index-4.html">Home Automobile</a></li>
							</ul></li>
						<li class=""><a href="javascript:0">Listing</a>
							<div class="megamenu">
								<div class="megamenu-row">
									<div class="col4">
										<ul class="list-unstyled">
											<li>
												<h5 class="heading">
													<i class="fa fa-map mr-2 text-primary" aria-hidden="true"></i>
													Half Screen Map
												</h5>
											</li>
											<li><a href="listings-half-screen-map-list.html">
													List Layout </a></li>
											<li><a href="listings-half-screen-map-grid.html">
													Grid Layout </a></li>
										</ul>
										<ul class="list-unstyled">
											<li>
												<h5 class="heading">
													<i class="fa fa-address-book mr-2 text-primary"
														aria-hidden="true"></i> Listing Without Map
												</h5>
											</li>
											<li><a
												href="listing-grid-right-sidebar-without-map.html">Grid
													Right Sidebar</a></li>
											<li><a href="listing-grid-fullwidth-without-map.html">Grid
													Fullwidth</a></li>
											<li><a
												href="listing-list-right-sidebar-without-map.html">List
													Right Sidebar</a></li>
											<li><a href="listing-list-fullwidth-without-map.html">List
													Fullwidth</a></li>
										</ul>
									</div>
									<div class="col4">
										<ul class="list-unstyled">
											<li>
												<h5 class="heading">
													<i class="fa fa-th-large text-primary mr-2"
														aria-hidden="true"></i> Listing Grid
												</h5>
											</li>
											<li><a href="listing-grid-left-sidebar.html">Left
													Sidebar</a></li>
											<li><a href="listing-grid-right-sidebar.html">Right
													Sidebar</a></li>
											<li><a href="listing-grid-fullwidth.html">Fullwidth</a>
											</li>
										</ul>
										<ul class="list-unstyled">
											<li>
												<h5 class="heading">
													<i class="fa fa-th-list text-primary mr-2"
														aria-hidden="true"></i> Listing List
												</h5>
											</li>
											<li><a href="listing-list-left-sidebar.html"> Left
													Sidebar</a></li>
											<li><a href="listing-list-right-sidebar.html"> Right
													Sidebar</a></li>
											<li><a href="listing-list-fullwidth.html"> Fullwidth</a>
											</li>
										</ul>
									</div>
									<div class="col4">
										<ul class="list-unstyled">
											<li>
												<h5 class="heading">
													<i class="fa fa-file-text text-primary mr-2"
														aria-hidden="true"></i> Single Listing
												</h5>
											</li>
											<li><a href="listing-store.html">Store Listing</a></li>
											<li><a href="listing-vendor.html">Vendor Listing</a></li>
											<li><a href="listing-event.html">Event Listing </a></li>
											<li><a href="listing-rental.html">Rental Listing </a></li>
											<li><a href="listing-reservation.html">Reservation
													Listing</a></li>
											<li><a href="listing-service.html">Service Listing </a>
											</li>
										</ul>
									</div>
								</div>
							</div></li>
						<li class=""><a href="javascript:0">Pages</a>
							<ul class="dropdown">
								<li><a href="contact-us.html">Contact Us</a></li>
								<li><a href="terms-of-services.html">Terms and
										Conditions</a></li>
								<li><a href="pricing-table.html">Pricing Table</a></li>
								<li><a href="how-it-works.html">How It Works</a></li>
								<li><a href="user-profile.html">User Profile</a></li>
								<li><a href="comming-soon.html">Coming Soon</a></li>
								<li><a href="404.html">404 Page</a></li>
							</ul></li>
						<li class=""><a href="blog.html">Blog</a></li>
						<li class=""><a href="javascript:0">Admin</a>
							<ul class="dropdown">
								<li><a href="javascript:0">User Admin</a>
									<ul class="dropdown">
										<li><a href="dashboard-user.html">Dashboard</a></li>
										<li><a href="my-bookings.html">My Booking</a></li>
										<li><a href="my-favourites.html">My Favourites</a></li>
										<li><a href="my-reviews.html">My reviews</a></li>
										<li><a href="profile-user.html">My Profile</a></li>
										<li><a href="message-users.html">My Messages</a></li>
									</ul></li>
								<li><a href="javascript:0">List Admin</a>
									<ul class="dropdown">
										<li><a href="dashboard-list-admin.html">Dashboard</a></li>
										<li><a href="my-listings.html">My Listings</a></li>
										<li><a href="add-listings.html">Add Listings</a></li>
										<li><a href="edit-listings.html">Edit Listings</a></li>
										<li><a href="admin-booking-requests.html">Admin
												Booking</a></li>
										<li><a href="admin-reviews.html">Admin Reviews</a></li>
										<li><a href="payment-process.html">Payment</a></li>
										<li><a href="profile-list-admin.html">My Profile</a></li>
										<li><a href="messages-list-admin.html">My Messages</a></li>
									</ul></li>
								<li><a href="javascript:0">Site Admin</a>
									<ul class="dropdown">
										<li><a href="dashboard-stie-admin.html">Dashboard</a></li>
										<li><a href="admin-listings.html">Admin Listings</a></li>
										<li><a href="user-list-owners.html">List Owners</a></li>
										<li><a href="user-generals.html">General Users</a></li>
										<li><a href="profile-site-admin.html">My Profile</a></li>
									</ul></li>
								<li><a href="login.html">Login</a></li>
								<li><a href="sign-up.html">Create Account</a></li>
							</ul></li>

					</ul>

				</div>
			</div>
		</nav>
	</header>