package com.S002;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.S001.S001PO;

/*
 * @author Crystal
 * orders
 * S002 訂單查詢 (在購物車內已被送出的訂單)
 */
@WebServlet("/insert")
public class S002InsertServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("S002Servlet-doGet Start！");

		S002Dao s002Dao = new S002Dao();
		String totalPrice = req.getParameter("totalPrice");
		String addOrder = s002Dao.addOrder(Integer.valueOf(totalPrice));
		List<S001PO> s005FindDataToList = (List<S001PO>) req.getSession().getAttribute("s005FindDataToList");

		s002Dao.orderDtl(s005FindDataToList, addOrder, Integer.valueOf(totalPrice));

		List<S002PO> s002FindAll = s002Dao.findAll();
		System.out.println("s002Insert:" + s002FindAll);

		req.getSession().removeAttribute("s001AddProdCodeToList");

		req.setAttribute("s002FindAll", s002FindAll);
		req.getRequestDispatcher("S002.jsp").forward(req, resp);
	}
}