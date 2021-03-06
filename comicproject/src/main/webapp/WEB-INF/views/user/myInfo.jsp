<%@page import="com.project.comic.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="uvo" value="${uvo }" scope="page" /> 


    <main>

<!-- ====================================
———	INTERESTED
===================================== -->

			

                <div class="col-md-12">
                    <div class="box">
                        <h1>내 정보</h1>
                        <p class="lead">개인 정보 변경을 원하시면 하단의 내용 변경 후 수정 버튼을 눌러주세요.</p>
                        <p class="text-muted">(ID, 이름, 생년월일은 수정 불가합니다.) </p>

                        <h3>수정 불가 정보</h3>

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="password_old">ID</label>
                                        <input type="text" class="form-control" id="password_old" value="${uvo.id}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="password_old">가입일</label>
                                        <input type="text" class="form-control" id="password_old" value="${uvo.sdate }" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="name">이름</label>
                                        <input type="text" class="form-control" id="name" value="${uvo.name}" readonly="readonly">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="birth">생년월일</label>
                                        <input type="text" class="form-control" id="birth" value="${uvo.birth}" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                             <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="name">잔여 포인트</label>
                                        <input type="text" class="form-control" id="point" value="${uvo.point}" readonly="readonly">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->


                        <hr>

                        <h3>수정 가능 정보</h3>
                        <form action="#" name="" id="" method="post">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="phone">연락처</label>
                                        <input type="tel" class="form-control" id="phone" name="phone" value="${uvo.phone}">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="lastname">주소 변경</label>
                                        <input type="text" class="form-control" id="addr" name="addr" value="변경 주소">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="pwd">변경 비밀번호 입력</label>
                                        <input type="password" class="form-control" id="pwd" placeholder="변경하실 비밀번호를 입력해주세요.">
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="pwdCheck">비밀번호 확인</label>
                                        <input type="password" class="form-control" id="pwdCheck" placeholder="확인 비밀번호를 입력해주세요.">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row -->

                            <div class="row">
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="city">우편번호</label>
                                        <input type="text" class="form-control" id="post" value="${uvo.post }" readonly="readonly">
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <label for="zip">주소</label>
                                        <input type="text" class="form-control" id="addr" value="${uvo.si } ${uvo.gu } ${uvo.dong }" readonly="readonly">
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-3">
                                    <div class="form-group">
                                        <label for="country">상세주소</label>
                                      <input type="text" class="form-control" id="detailaddr" value="${uvo.detail }" readonly="readonly">
                                    </div>
                                </div>

                                <div class="col-sm-12 text-center">
                                    <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i>수정</button>

                                </div>
                            </div>
                        </form>
                    </div>
                </div>





  