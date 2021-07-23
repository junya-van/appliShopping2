<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ヘッダー画面 --%>
<div align="right">
	<%-- ログイン済みの場合はIDを表示 --%>
	ようこそ「<c:out value="${user_db.name}"/>」さん!
	<a href="${pageContext.request.contextPath}/LoginServlet?submit=history">購入履歴</a>
	<a href="${pageContext.request.contextPath}/CartServlet">カート</a>
	<a href="${pageContext.request.contextPath}/LoginServlet?submit=ログアウト">ログアウト</a>
</div>