<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
var msg = '${msg}';
window.alert(msg.replace("<br>","\n"));
location.replace('${gourl}'); /* 메인으로 돌아가기 */
</script>