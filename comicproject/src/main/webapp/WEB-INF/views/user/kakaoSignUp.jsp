<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal-body">

	<h2 style="text-align: center;">추가 정보 입력</h2>
	<form action="/comic/signup.do" method="post" name="signup"
		onsubmit="return checkValue()">
		<input type="hidden" name="id" value="${id}" /> 
			<input type="hidden" name="addr"
			id="kakaoaddr" value="" />
		<div class="form-group">
			<span>이 름 : </span> <input type="text" class="form-control"
				name="name" value="" placeholder="이름 입력" required>
		</div>
		<div class="form-group">
			<span>주 소</span> <input type="text" class="form-control"
				id="kakaoaddrf" placeholder="검색 버튼 클릭" readonly>
			<p class="text-center" style="margin-top: 16px;">
				<input type="button" onclick="openApiAddr('kakao')" value="검색"
					class="btn btn-primary">
			</p>
		</div>

		<div class="form-group">
			<span>상세 주소</span> <input type="text" class="form-control"
				name="addrd" id="kakaoaddrd" placeholder="나머지 상세 주소 입력" required>
		</div>


		<div class="form-group">
			<span>연락처</span> <input type="tel" class="form-control" name="phone"
				id="kakaophone" maxlength="12"
				placeholder="핸드폰 번호 (ex: 01039063915)" onchange="addrAppend()"
				required>
		</div>
		<div class="form-group">
			<span>생년월일</span> <input type="date" class="form-control"
				name="birth" id="kakaobirth" required>
		</div>




		<p class="text-center">
			<button class="btn btn-primary" type="submit">
				<i class="fa fa-sign-in"></i> 회원가입
			</button>
		</p>


	</form>

</div>