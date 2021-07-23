<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ログイン失敗画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン失敗</title>
	</head>
	<body>
		<h1>ログインエラー</h1>
		<p>入力されたユーザが存在しません</p>
		<form>
			<input class="common_button" type="button" onclick="location.href='${pageContext.request.contextPath}/'" value="戻る"/>
		</form>
	</body>
</html>