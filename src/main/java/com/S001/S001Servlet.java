package com.S001;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Crystal
 * Index 首頁
 * S001 findAllProduct
 */
@WebServlet("/index")
public class S001Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("S001Servlet Start！");
		
		S001Dao s001Dao = new S001Dao();
		List<S001PO> s001ProductList = s001Dao.findAllProduct();

		// (Key, value)
		req.setAttribute("s001ProductList", s001ProductList);
		
		// 轉跳頁面
		// 使用forward由於內部呼叫,在client上並不會看到程式的名稱
		req.getRequestDispatcher("S001.jsp").forward(req, resp);
	}
}