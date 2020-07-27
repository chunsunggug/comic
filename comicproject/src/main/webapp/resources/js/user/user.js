function onlyNumberInput2(Ev) {
	if (window.event) // IE코드
		var code = window.event.keyCode;
	else
		// 타브라우저
		var code = Ev.which;

	if ((code > 34 && code < 41) || (code > 47 && code < 58)
			|| (code > 95 && code < 106) || code == 8 || code == 9
			|| code == 13 || code == 46) {
		window.event.returnValue = true;
		return;
	}

	if (window.event)
		window.event.returnValue = false;
	else
		Ev.preventDefault();
}
/*address api*/
function openApiAddr() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var addrA = ''; // 합친 주소 변수
			var extraAddr = ''; // 참고항목 변수

			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다. 신주소 주석처리
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName
							: data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("addrd").value = extraAddr;

			} else {
				document.getElementById("addrf").value = '';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('addrf').value = data.zonecode;
			document.getElementById("addrf").value = addr;
			addr = data.jibunAddress; // 지번으로 사용예정
			document.getElementById("addr").value = data.zonecode + " " + addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("addrd").focus();
		}
	}).open();
}
/*submit to check login*/
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
		if (document.signin.pwd.value.length < 8) {
			alert("비밀번호를 확인하세요.");
			document.signup.pwd.focus();
			return false;
		}
	}
}


/*address hidden value add and value pattern check submit to check signup value*/
function checkValue() {
	var phone = document.signup.phone.value;
	var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
	var id = document.signup.id.value;
	if (!document.signup.id.value) {
		alert("아이디를 다시 확인하세요.");
		return false;
	}
	if (!pwdCheck.test(document.signup.pwd.value)) {
		alert("비밀번호를 확인하세요.");
		document.signup.pwd.focus();
		return false;
	}
	if (document.signup.pwd.value != document.getElementById("pwdcheck").value) {
		alert("비밀번호를 동일하게 입력하세요.");
		document.getElementById("pwdcheck").focus();
		return false;
	}
	if (!regExp.test(phone)) {
		alert('전화 번호를 정확히 입력해주세요.');
		document.signup.phone.focus();
		return false;
	}
	document.getElementById("addr").value = document.getElementById("addr").value
			+ " " + document.getElementById("addrd").value;
}


/*회원가입 하단의 Ajac첫번째 파라미터 url 경로 destnation , 두번째 파라미터는 보낼 데이터 값을 입력 ,	세번째 파라미터 펑션으로 실행하는데 데이터 값을 받음  리턴 받는 이름 data 리턴 받은 data를 통해 확인*/
function FindIdAjax() {

	var name = $("#findIdname").val();
	var birth = $("#findIdbirth").val();
	console.log('check' + name + "||" + birth);

	if (name.length < 1 || birth.length != 10) {
		$("#checkFindMes").html("이름 또는 생년월일을 확인해주세요.").css("color", "red");
	} else {
		$
				.post(
						"findId.do",
						{
							"name" : name,
							"birth" : birth
						},
						function(data) {
							if (data == '0') {
								$("#checkFindMes").html("가입된 계정이 없습니다.").css(
										"color", "red");
							} else {
								var result = data.split(" ");
								console.log('check data:' + result.length);
								$("#checkFindMes")
										.html(
												"동명 이인 아이디 검색의 주의하세요.<br>정보보호를 위해 일부는 *로 표기됩니다.<br>전체는 비밀번호 찾기 확인 바랍니다.")
										.css("color", "green");
								for (var i = 0; i < result.length; i++) {
									console.log('check data:' + result[i]);
									$("#checkFindMes").append(
											"<br>" + result[i]);
								}

							}
						});
	}
}

/*phone number check*/
function addrAppend() {
	$("#phone").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g, ""));
	});
}

/*submit to check findid*/
function checkId() {
	var id = $("#id").val();
	var check = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if (!check.test(id)) {
		$("#checkEmailup").html("EMAIL 형식이 아닙니다.").css("color", "red");
	} else {
		$.post("idCheck.do", {
			"id" : $("#id").val()
		}, function(data) {
			if (data == '0') {
				$("#checkEmailup").html("사용가능한 EMAIL 입니다.").css("color",
						"green");
				$("#input_pwd").focus();
			} else {
				$("#checkEmailup").html("사용불가능한 EMAIL 입니다.")
						.css("color", "red");
				$("#input_email").focus();
			}
		});
	}
}

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