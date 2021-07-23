<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ユーザ登録結果画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザ登録結果</title>
	</head>
	<body>
		<c:out value="${msg}" />
		<form action="${pageContext.request.contextPath}/" method="get">
			<input class="common_button" type="submit" value="戻る">
		</form>
	</body>
</html>