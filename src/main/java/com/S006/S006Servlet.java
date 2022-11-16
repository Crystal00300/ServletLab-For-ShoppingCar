package com.S006;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.S002.S002Dao;
import com.S003.S003PO;

@WebServlet("/orderDtl")
public class S006Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("S005Servlet Start");

		// 訂單明細
		S002Dao s002Dao = new S002Dao();
		String orderDtl = req.getParameter("orderDtl");
		System.out.println("orderDtl:" + orderDtl);
		if (orderDtl != null) {
			List<S003PO> orderDtlList = s002Dao.findOrderDtl(orderDtl);
			System.out.println(orderDtlList);
			req.setAttribute("orderDtlList", orderDtlList);
		}

		// 轉跳到顯示頁面
		req.getRequestDispatcher("S006.jsp").forward(req, resp);
	}
}
