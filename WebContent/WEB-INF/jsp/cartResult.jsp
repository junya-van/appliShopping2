<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- カート結果画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<h1>カートに追加しました</h1>
		<div align="left">
			<form action="${pageContext.request.contextPath}/ShoppingServlet" method="post">
				<input class="common_button" type="submit" value="戻る">
		    </form>
		</div>
		<div align="right" style="display:inline-flex">
			<form action="${pageContext.request.contextPath}/CartServlet" method="get">
				<input class="common_button" type="submit" value="カート">
			</form>
			<form action="${pageContext.request.contextPath}/BuyItemServlet" method="get">
				<input class="common_button" type="submit" value="レジへ">
			</form>
		</div>
	</body>
</html>