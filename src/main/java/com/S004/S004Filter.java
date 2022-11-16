package com.S004;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(value = { "/shopcar", "/orders" })
public class S004Filter implements Filter {

	/*
	 * WEB伺服器每次在呼叫web資源的service方法之前， 都會先呼叫一下filter的doFilter方法
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Login Filter Start");

		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;

		System.out.println("URI : " + request.getRequestURI());
		
		String uri = request.getRequestURI();
		System.out.println("uri:" + uri);

		// 有一個user名稱的包裹，裡面裝了使用者端的訊息等資訊，把他放進另外切分出來的一個記憶體位置
		if (request.getSession().getAttribute("user") != null) {
			
			// 通過filter，放行
			chain.doFilter(req, resp);
			return;
		}
		
//		if(uri.endsWith("/shopcar")) {
//			System.out.println("不攔截");
//			request.getSession().getAttribute("user");
//			response.sendRedirect("login");
//			return;
//		}
		
		// endsWith：指定的後綴詞(結尾)
		if (uri.endsWith("/login")) {
			chain.doFilter(req, resp);
			return;
		}
		req.getRequestDispatcher("S004.jsp").forward(req, resp);
		chain.doFilter(req, resp);
	}
}