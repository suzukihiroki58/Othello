<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Board"%>
<%@ page import="model.Stone"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Othello</title>
<link rel="stylesheet" href="css/othello.css">
</head>
<body>
	<a href="MenuServlet">メニューへ戻る</a>
	<h2>先手： <%= request.getAttribute("humanFirst") %></h2>
	<h2>後手： <%= request.getAttribute("humanSecond") %></h2>
    <div class="board">
        <%
        Board board = new Board();
        for (int x = 0; x < 8; x++) {
        %>
        <div class="row">
            <%
            for (int y = 0; y < 8; y++) {
                Stone stone = board.getStoneAt(x, y);
                String stoneClass = "";
                if (stone == Stone.BLACK) {
                    stoneClass = "stone black";
                } else if (stone == Stone.WHITE) {
                    stoneClass = "stone white";
                }
            %>
            <div class="cell">
                <% if (!stoneClass.isEmpty()) { %>
                    <div class="<%= stoneClass %>"></div>
                <% } %>
            </div>
            <%
            }
            %>
        </div>
        <%
        }
        %>
    </div>
</body>
</html>
