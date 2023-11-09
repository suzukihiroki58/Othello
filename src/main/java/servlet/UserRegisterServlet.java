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

@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/userRegister.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String regex = "^[a-zA-Z0-9!-/:-@\\[-`{-~]+$";
	    if (!password.matches(regex)) {
	        request.setAttribute("errorMessage", "パスワードは英数字記号で入力してください");
	        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/userRegister.jsp");
	        rd.forward(request, response);
	        return;
	    }
		
		if (UsersDAO.isUserNameExists(username)) {
			request.setAttribute("errorMessage", "このユーザー名は既に使われています");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/userRegister.jsp");
			rd.forward(request, response);
			return;
		}

		User user = new User();
		user.setUserName(username);
		user.setPassword(password);

		UsersDAO.registerUser(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
		rd.forward(request, response);
	}

}