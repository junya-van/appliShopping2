<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 購入履歴画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入履歴</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<h1><c:out value="${user_db.name}"/>さんの購入履歴</h1>
		<table class="table_list">
			<tbody>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>個数</th>
					<th>購入日</th>
				</tr>
				<%-- リクエストスコープから表示する値を取得 --%>
				<c:forEach var="bean" items="${history}">
					<tr>
						<td><c:out value="${bean.itemId}"/></td>
						<td><c:out value="${bean.itemName}"/></td>
						<td><c:out value="${bean.quantity}"/></td>
						<td><c:out value="${bean.date}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="${pageContext.request.contextPath}/ShoppingServlet" method="post">
			<input class="common_button" type="submit" value="一覧に戻る">
		</form>
	</body>
</html>