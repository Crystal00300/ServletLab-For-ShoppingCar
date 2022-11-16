package com.S002;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author Crystal
 * orders
 * S002 訂單查詢 (在購物車內已被送出的訂單)
 */
@WebServlet("/orders")
public class S002Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("S002Servlet-doGet Start！");

		// 取消訂單
		S002Dao s002Dao = new S002Dao();
		String cancelledOrder = req.getParameter("cancelledOrder");
		System.out.println("cancelledOrder:" + cancelledOrder);
		if (cancelledOrder != null) {
			s002Dao.updateOrder(cancelledOrder);
		}

		List<S002PO> s002FindAll = s002Dao.findAll();
		System.out.println("s002FindAll:" + s002FindAll);
		
		req.setAttribute("s002FindAll", s002FindAll);
		// 轉跳頁面
		// forward:內部轉跳
		req.getRequestDispatcher("S002.jsp").forward(req, resp);
	}
}