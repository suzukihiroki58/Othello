<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
	<h2>新規ユーザー登録ページ</h2>
	<form action="UserRegister" method="post">
		<label>ユーザー名</label> <input type="text" name="username"
			placeholder="30字以内" required> <br> <label>パスワード</label>
		<input type="password" name="password" placeholder="30字以内の英数字記号" required>
		<br> <input type="submit" value="登録" class="button">
	</form>
	<% if (request.getAttribute("errorMessage") != null) { %>
        <p><%= request.getAttribute("errorMessage") %></p>
    <% } %>
</body>
</html>