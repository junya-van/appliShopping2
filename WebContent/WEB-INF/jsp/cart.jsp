<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- カート画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<h1>カート情報</h1>
		<form action="${pageContext.request.contextPath}/CartDeleteServlet" method="get">
			<c:choose>
				<%-- カートに商品が入っている場合の処理 --%>
				<c:when test="${not empty cart}">
					<table>
						<tbody>
							<tr>
								<th>商品ID</th>
								<th>商品名</th>
								<th>個数</th>
								<th></th>
							</tr>

							<%-- beanの要素数分(カートに入ってる商品の種類分)テーブル作成 --%>
							<c:forEach var="bean" items="${cart}">
								<tr>
									<td><c:out value="${bean.itemId}"/></td>
									<td><c:out value="${bean.itemName}"/></td>
									<td><c:out value="${bean.count}"/></td>
									<%-- 削除ボタン --%>
									<%-- カートに入ってる商品ごとに削除ボタンが付くので、商品IDと削除ボタンを紐づけておく --%>
									<td class="button">
										<input class="common_button" type="submit" value="削除" name="${bean.itemId}">
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<%-- カートに何も入っていない場合 --%>
				<c:otherwise>
					カートには何も入ってません
				</c:otherwise>
			</c:choose>
		</form>

		<div align="left">
			<form action="${pageContext.request.contextPath}/ShoppingServlet" method="post">
				<input class="common_button" type="submit" value="戻る">
		    </form>
		</div>

		<%-- カートに商品が入っている場合のみレジへボタンを表示する --%>
		<c:if test="${not empty cart}">
			<div align="right">
				<form action="${pageContext.request.contextPath}/BuyItemServlet" method="get">
					<input class="common_button" type="submit" value="レジへ">
				</form>
			</div>
		</c:if>

	</body>
</html>