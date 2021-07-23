<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ユーザ登録フォーム画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザ登録フォーム</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/InsertServlet" method="post">
			<table class="table_form">
				<tbody>
					<tr>
						<th>ユーザID</th>
						<td><input type="text" name="id" pattern="^([0-9a-zA-Z]{5})$" required> ※半角英数で5文字</td>
					</tr>
					<tr>
						<th>名前</th>
						<td><input type="text" name="name" required></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="pass" pattern="^[0-9A-Za-z]+$" required> ※半角英数</td>
					</tr>
					<tr>
						<th>年齢</th>
						<td><input type="text" name="age" pattern="[0-9]{1,}" required> ※半角数字</td>
					</tr>
				</tbody>
			</table>
			<input class="common_button" type="button" onclick="location.href='${pageContext.request.contextPath}/'" value="戻る">
			<input class="common_button" type="submit" value="登録">
		</form>
	</body>
</html>