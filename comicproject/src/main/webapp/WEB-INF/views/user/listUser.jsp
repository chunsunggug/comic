<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		
		<div class="col-md-12">
			<div class="container-fluid text-center">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>번호</th>
							<th>ID</th>
							<th>이름</th>
							<th>연락처</th>
							<th>주소</th>
							<th>포인트</th>
							<th>생년월일</th>
							<th>가입일</th>
							<th>상태</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${userList}">
							<tr>
								<th style="vertical-align: middle">${list.uidx}</th>
								<th style="vertical-align: middle">${list.id}</th>
								<th style="vertical-align: middle">${list.name}</th>
								<th style="vertical-align: middle">${list.phone}</th>
								<th style="vertical-align: middle">${list.post} ${list.si} ${list.gu} ${list.dong} ${list.detail} </th>
								<th style="vertical-align: middle">${list.point}</th>
								<th style="vertical-align: middle">${list.birth}</th>
								<th style="vertical-align: middle">${list.sdate}</th>
								<th style="vertical-align: middle">${list.isyn}</th>
								<th style="vertical-align: middle">
									<button class="btn btn-danger"
										onclick="deleteUser(${list.uidx})">강제 탈퇴</button>
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<%-- <div class="container-fluid">
				<ul class="pagination">${pagestr}
				</ul>
			</div> --%>
		</div>


<script
	src="/comic/resources/js/store/store-book.js?v=<%=System.currentTimeMillis() %>"></script>