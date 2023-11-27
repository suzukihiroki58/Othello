package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HumanPlayer;
import model.Othello;
import model.User;

@WebServlet("/OthelloServlet")
public class OthelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String username = (user != null) ? user.getUserName() : "未ログイン";

		Othello othello = new Othello();
		othello.startGame();

		String humanFirst = (othello.getFirstPlayer() instanceof HumanPlayer) ? username : "CPU";
		String humanSecond = (othello.getSecondPlayer() instanceof HumanPlayer) ? username : "CPU";

		request.setAttribute("humanFirst", humanFirst);
		request.setAttribute("humanSecond", humanSecond);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/othello.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
