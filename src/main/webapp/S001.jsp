<%@page import="com.S001.S001PO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
	transition: all 0.3s ease-in-out;
}

.container {
	clear: both;
	overflow: auto;
}

nav {
	float: right;
}

.logo img {
	float: left;
}

ul li {
	display: inline-block;
	padding: 10px;
	font-size: 20px;
	font-family: raleway;
}

ul li:hover {
	color: red;
}
</style>
</head>
<body>
	<div class="container">
		<div class="logo">
			<img
				src="https://atimetoshop.com/wp-content/uploads/2019/06/A-time-to-shop-Logo.png"
				alt="" width="200" />
		</div>
		<nav>
			<ul>
				<li onclick="location.href='index'">首頁</li>
				<li onclick="location.href='orders'">訂單查詢</li>
				<li onclick="location.href='shopcar'">購物車</li>
			</ul>
		</nav>
	</div>
	<hr />
	<main>

		<div style="width: 1000px; margin: 0 auto;">
			<table>
				<tr>

					<%
					List<S001PO> s001ProductList = (List<S001PO>) request.getAttribute("s001ProductList");
					for (int i = 0; i < s001ProductList.size(); i++) {
						S001PO product = s001ProductList.get(i);
					%>

					<td rowspan="3" style="vertical-align: top; padding-top: 10px;">
						<img src="<%=product.getImgLink()%>" width="200"
						style="padding-right: 20px;">
					</td>
					<td style="vertical-align: middle; height: 40px;"><span
						style="color: blue"><%=product.getProdName()%></span></td>
				</tr>
				<tr>
					<td style="vertical-align: top; height: 120px; width: 800px;">
						<%=product.getProdDesc()%>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: middle; text-align: right;">
						<hr> <b>價格：<span style="color: red"><%=product.getProdAmt()%></span></b> <a
						href="addShopCar?prod=<%=product.getProdCode()%>"> <img style="vertical-align: middle;"
							src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4A1jV-g_TxYq3gIIuT76Tatt-iawfwsFmioSCYM_6wm--wRfk0eiOMQUMSdIpNBOq8vw&usqp=CAU"
							width="120">
					</a>
					</td>
				</tr>
				<%
				}
				%>

			</table>
		</div>
	</main>
</body>
</html>