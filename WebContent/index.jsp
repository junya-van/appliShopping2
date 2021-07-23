<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ログイン画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン画面</title>
	</head>
	<body>
		<h1>ようこそショッピング風サイトへ</h1>
		<h3>ポートフォリオ用に作りました</h3>
		<p>ログインIDとパスワードを入力して下さい</p>
		<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
			<table class="table_form">
				<tbody>
					<tr>
						<%-- ログイン済みの場合はIDを表示 --%>
						<th>ログインID</th>
						<td><input type="text" name="id" value="${user_db.id}"/></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="pass"/></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" name="submit" value="ログイン"/>
			<%-- ログイン済みの場合はログアウトボタンを表示。そうでない場合はサインアップボタンを表示 --%>
			<c:choose>
				<c:when test="${login_db == 'login'}">
					<input type="submit" name="submit" value="ログアウト"/>
				</c:when>
				<c:otherwise>
					<input class="common_button" type="button" onclick="location.href='${pageContext.request.contextPath}/InsertServlet'" value="サインアップ">
				</c:otherwise>
			</c:choose>
		</form>
	</body>
</html>