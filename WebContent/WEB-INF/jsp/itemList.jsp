<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 商品一覧画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品一覧</title>
	</head>
	<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<h1>商品一覧</h1>
		<form action="${pageContext.request.contextPath}/ShoppingServlet" method="get">
			<table class="table_list">
				<tbody>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>単価</th>
						<th>在庫数</th>
					</tr>

					<%-- Beanの要素数分(商品の種類分)テーブルを作成 --%>
					<c:forEach var="bean" items="${itemList}">
						<tr>
							<td><c:out value="${bean.itemId}"/></td>
							<td><c:out value="${bean.itemName}"/></td>
							<td><c:out value="${bean.price}"/></td>
							<td><c:out value="${bean.quantity}"/></td>
							<%-- 詳細ボタン --%>
							<%-- 各商品ごとに詳細ボタンが付くので、詳細ボタンと商品IDを紐づけておく --%>
							<td class="button">
								<input class="common_button" type="submit" value="詳細" name="${bean.itemId}">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input class="common_button" type="button" onclick="location.href='${pageContext.request.contextPath}/'" value="戻る">
		</form>
	</body>
</html>