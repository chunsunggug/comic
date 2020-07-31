<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/comic/resources/js/jquery-1.11.0.min.js"></script>
<script>
var check = window.confirm('${msg}');
var id = '${id}';
if(check){
	console.log("if 안 id 확인 : "+'${id}');
	$.post(
			"/comic/outCancle.do",
			{
				"id" : '${id}'
			},
			function(data) {
				console.log('data 확인 : '+data);
				if (data == "1") {
					console.log('data:1');
					alert('탈퇴 취소가 완료되었습니다. 로그인 후 이용 바랍니다.');
		    		location.replace('${gourl}'); /* 메인으로 돌아가기 */
				} else {
					console.log('data:0');
					alert('에러 발생으로 다시 진행해주세요.');
					location.replace('${gourl}'); /* 메인으로 돌아가기 */
				}
			});
	
}else{
	
	location.replace('${gourl}'); /* 메인으로 돌아가기 */
}
</script>
<!-- /* 	$.ajax({
	    url:'/comic/outCancle.do',
	    type:'post', // 메소드(get, post, put 등)
	    data:{'id':'${id}'},
	    success: function(data) {
	    	if(data==1){
	    		alert('탈퇴 취소가 완료되었습니다.\n로그인 후 이용 바랍니다.')
	    		location.replace('/comic/index.do'); /* 메인으로 돌아가기 */
	    	}
	    	
	        //서버로부터 정상적으로 응답이 왔을 때 실행
	    },
	    error: function(err) {
	    	alert('에러가 발생으로 다시 진행해주세요.');
	    }
	});
	
	 */ -->