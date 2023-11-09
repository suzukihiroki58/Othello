<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h2>ログインページ</h2>
	<form action="LoginServlet" method="post">
		<label>ユーザー名</label> <input type="text" name="username" required>
		<br> <label>パスワード</label> <input type="password" name="password"
			required> <input type="submit" value="ログイン">
	</form>
	<p>
		アカウントをまだ持っていない方<br> <a href="UserRegister"><button>新規登録ページへ</button></a>
	</p>
	<%if (request.getAttribute("errorMessage") != null) { %>
		<%=request.getAttribute("errorMessage")%>
	<%} %>
</body>
</html>