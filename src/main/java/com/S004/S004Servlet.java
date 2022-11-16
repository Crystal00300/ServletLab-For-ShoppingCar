package com.S004;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Crystal
 * login
 * S004 登入
 */
@WebServlet("/login")
public class S004Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet Start");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("username:" + username);
		System.out.println("password:" + password);

		S004Dao s004Dao = new S004Dao();

		// 使用者 和 密碼 不能是空的
		if (username != null && password != null) {
			List<S004PO> user = s004Dao.LoginByUser(username, password);
			System.out.println("user:" + user);

			// 如果是空的
			if (!user.isEmpty()) {
				req.getSession().setAttribute("user", user);
				System.out.println("req:" + req);

				resp.sendRedirect("shopcar");
				return;
			} else {
				return;
			}
		}
		req.getRequestDispatcher("S004.jsp").forward(req, resp);
	}

	/**
	 * forward：內部轉址,URL不會顯示程式名稱。 sendRedirect：直接外部呼叫另一支程式,會顯程式名稱，適用於跳至外部網站或回主畫面使用。
	 */
}
