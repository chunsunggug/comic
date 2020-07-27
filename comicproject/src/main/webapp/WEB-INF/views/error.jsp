<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="bg-light py-10">
  
  <div class="container">
    <div class="row justify-content-center">
        <div class="text-content-404">
          <center>
          <h1 class="text-primary" style="font-size: 10em; margin-top: 40px;">오류 발생</h1>
          </center>
          
          <center>
          <p class="mb-9" style="margin: 40px 0;">페이지가 존재하지 않거나, 오류가 발생하였습니다.<br>
          	관리자에게 문의하여주시기 바랍니다.
          </p>
          </center>
        </div>
        
         <form action="/comic/index.do">
         <div class="input-group" style="margin-bottom: 40px;">
	      <input type="text" class="form-control" placeholder="메인으로 돌아가시려면 우측 버튼을 클릭해주세요.">
	      <span class="input-group-btn">
		<button class="btn btn-primary" type="submit">메인으로....</button>
	      </span>
	    </div><!-- /input-group -->
        </form>
    </div>
  </div>
</section>