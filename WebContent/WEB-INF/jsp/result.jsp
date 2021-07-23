<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 購入結果画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>結果</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<h1>購入結果</h1>
		購入しました
		<form action="${pageContext.request.contextPath}/ShoppingServlet" method="post">
			<input class="common_button" type="submit" value="一覧に戻る">
		</form>
	</body>
</html>