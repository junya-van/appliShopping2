<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 詳細画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>詳細画面</title>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<h1>商品詳細</h1>

		商品ID:<c:out value="${item.itemId }"/><br>
		商品名:<c:out value="${item.itemName }"/><br>
		単価:<c:out value="${item.price }"/><br>
		<c:out value="${item.text }"/><br>

		<div align="left">
			<form action="${pageContext.request.contextPath}/ShoppingServlet" method="post">
				<input class="common_button" type="submit" value="戻る">
		    </form>
		</div>

		<div align="right">
			<c:out value="${errorMsg}"/>
		</div>

		<form action="${pageContext.request.contextPath}/CartServlet" method="post">
		<div align="right">
			<c:choose>
				<c:when test="${item.quantity != 0 }">
					<%-- 数量(在庫)がある場合はリストボックスと「カートへ」ボタンを表示 --%>
					<select class="list" name="${item.itemId += 'list' }">
						<c:forEach var="i" begin="1" end="${item.quantity}" step="1">
							<option value=<c:out value="${i}"/>><c:out value="${i}"/></option>
						</c:forEach>
					</select>
					<input class="common_button" type="submit" value="カートへ">
				</c:when>
				<c:otherwise>
					<%-- 数量(在庫)がない場合は売り切れと表示 --%>
					売り切れ
				</c:otherwise>
			</c:choose>
		</div>
		</form>
	</body>
</html>