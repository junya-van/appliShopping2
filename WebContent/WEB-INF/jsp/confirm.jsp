<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 購入確認画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>購入確認</title>
	</head>
	<body>
		<table>
			<tbody>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>個数</th>
				</tr>

				<%-- beanの要素数分(カートに入ってる商品の種類分)テーブル作成 --%>
				<c:forEach var="bean" items="${cart}">
					<tr>
						<td><c:out value="${bean.itemId}"/></td>
						<td><c:out value="${bean.itemName}"/></td>
						<td><c:out value="${bean.count}"/></td>
					</tr>
				</c:forEach>
					<tr>
						<td></td>
						<th>合計</th>
						<td><c:out value="${total}"/>円</td>
					</tr>
			</tbody>
		</table>

		この内容で確定しますか？

		<div align="left">
			<form action="${pageContext.request.contextPath}/CartServlet" method="get">
				<input class="common_button" type="submit" value="戻る">
		    </form>
		</div>

		<div align="right">
			<form action="${pageContext.request.contextPath}/ResultServlet" method="post">
				<input class="common_button" type="submit"  value="確定する">
			</form>
		</div>

	</body>
</html>