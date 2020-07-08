<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>API 연습</title>
</head>

<body>
    <h1>책 제목을 검색해주세요.</h1>
    <input id="bookName" type="text">
    <button id="search">검색</button>
    <p></p>

    <script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>

    <script>
        $(function () {

            $("#search").click(function () {

                $.ajax({
                    method: "POST",
                    url: "https://dapi.kakao.com/v3/search/book?target=title", // 전송 주소
                    data: { query: $("#bookName").val() }, // 보낼 데이터
                    headers: {'Authorization': 'KakaoAK 118237743806f276d679025f706c0e3c' }
                })
                    .done(function (msg) { // 응답이 오면 처리를 하는 코드
                        console.log(msg);
                    });
            })
        });

    </script>
</body>

</html>