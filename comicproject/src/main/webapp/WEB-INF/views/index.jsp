<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
%>
<c:set var="include" value="${include }" scope="page" />
<jsp:include page="00_head.jsp"></jsp:include>
<jsp:include page="10_header.jsp"></jsp:include>
<c:choose>
<c:when test="${page ne null}">
<jsp:include page="${page}"></jsp:include>
</c:when>
<c:otherwise>
<jsp:include page="20_main.jsp"></jsp:include>
</c:otherwise>

</c:choose>
<jsp:include page="30_footer.jsp"></jsp:include>