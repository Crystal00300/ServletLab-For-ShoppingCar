package com.S005;

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
 * shopCar
 * (S003結束後) session帶著 prodCode 去資料庫找相對應的資料
 * 裝進新建的List 放回servlet ->jsp (購物車呈現)
 */
@WebServlet("/shopcar")
public class S005Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("S005Servlet Start");

		Object prodCodeSession = req.getSession().getAttribute("s001AddProdCodeToList");
		List<String> prodCodeSessionList = (List<String>) prodCodeSession;

		S005Dao s005Dao = new S005Dao();
		String prodCode = req.getParameter("prodCode");
		System.out.println("prodCode:" + prodCode);

		List<S001PO> s005FindDataToList = null;
		
		if (prodCodeSessionList != null) {
			for (int i = 0; i < prodCodeSessionList.size(); i++) {
				if (prodCodeSessionList.get(i).equals(prodCode)) {
					// 移除session
					prodCodeSessionList.remove(i);
					s005FindDataToList = s005Dao.findDataToList(prodCodeSessionList);
				} else {
					s005FindDataToList = s005Dao.findDataToList(prodCodeSessionList);
				}
			}
		}

		// 訂單明細
//		S002Dao s002Dao = new S002Dao();
//		String orderDtl = req.getParameter("orderDtl");
//		System.out.println("orderDtl:" + orderDtl);
//		if (orderDtl != null) {
//			s002Dao.findOrderDtl(orderDtl);
//		}

		System.out.println("s005FindDataList:" + s005FindDataToList);

		req.getSession().setAttribute("s005FindDataToList", s005FindDataToList);
		System.out.println("s005reqTest:" + req);

		// 轉跳到顯示頁面
		req.getRequestDispatcher("S005.jsp").forward(req, resp);
	}
}