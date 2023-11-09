package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = new User();
		user.setUserName(username);
		user.setPassword(password);

		User returnUser = UsersDAO.findUser(user);

		if (returnUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", returnUser);
			session.setAttribute("userId", returnUser.getUserId());

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/menu.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("errorMessage", "ユーザー名またはパスワードが間違っています");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
			rd.forward(request, response);
		}
	}
}