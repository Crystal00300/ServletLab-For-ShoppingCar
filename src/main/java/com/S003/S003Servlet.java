package com.S003;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * @author Crystal
 * addShopCar 加入購物車 (buy now)
 * S003 Prouduct 裝進List 存進session
 */
@WebServlet("/addShopCar")
public class S003Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("S003Servlet Start！");

		String s001ProdCode = req.getParameter("prod");
		Object s001AddSession = req.getSession().getAttribute("s001AddProdCodeToList");
		
		if (s001ProdCode != null) {
			List<String> s001AddProdCodeToList = new ArrayList<String>();

			if (s001AddSession == null) {
				s001AddProdCodeToList.add(s001ProdCode);
				System.out.println("第一次加入購物車:" + s001AddProdCodeToList);

			} else {
				s001AddProdCodeToList = (ArrayList<String>) s001AddSession;
				System.out.println("第n次加入購物車:" + s001AddProdCodeToList);
				if (s001AddProdCodeToList.contains(s001ProdCode)) {

				} else {
					s001AddProdCodeToList.add(s001ProdCode);
				}

			}

			req.getSession().setAttribute("s001AddProdCodeToList", s001AddProdCodeToList);
			
			// sendRedirect：名稱會顯示在url上
			resp.sendRedirect("index");
			System.out.println("購物車:" + s001AddProdCodeToList);
			return;
		}

	}

}