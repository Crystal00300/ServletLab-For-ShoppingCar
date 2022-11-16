<%@page import="com.S002.S002PO"%>
<%@page import="com.S001.S001PO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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

table, th, td {
	border: solid 1px #000;
	padding: 10px;
}

table {
	border-collapse: collapse;
	caption-side: bottom;
	width: 100%;
}

caption {
	font-size: 16px;
	font-weight: bold;
	padding-top: 5px;
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

	<form action="/ShopCarLab/insert">
		<table>
			<thead>
				<tr>
					<th>序號</th>
					<th>購買商品</th>
					<th>單價</th>
					<th></th>
				</tr>
			</thead>
			<tbody>

				<%
				List<S001PO> s005ShopCar = (List<S001PO>) request.getSession().getAttribute("s005FindDataToList");
				System.out.println("s005ShopCar:" + s005ShopCar);
				int total = 0;
				if (s005ShopCar != null) {
					for (int i = 0; i < s005ShopCar.size(); i++) {
						S001PO product = s005ShopCar.get(i);
						total += Integer.valueOf(product.getProdAmt());
				%>

				<tr>
					<td><%=i + 1%></td>
					<td><a href="#"><%=product.getProdName()%></a></td>
					<td style="text-align: right;"><%=product.getProdAmt()%></td>
					<td><a href="shopcar?prodCode=<%=product.getProdCode()%>">移除</a></td>
				</tr>

				<%
				}
				%>

				<tr>
					<%
					if (s005ShopCar.size() == 0) {
					%>
					<h2 style="color: red">購物車已清空！</h2>
					<%
					} else {
					%>
					<td></td>
					<td></td>
					<td style="text-align: right;">合計金額：<span style="color: red;"><%=total%></span></td>
					<td><button onclick="location.href='insert'">確認訂單</button></td>
					<%
					}
					}
					%>
				</tr>

			</tbody>
		</table>
		<%-- 		<input type="hidden" name="s005ShopCar" value="<%=s005ShopCar%>"> --%>
		<input type="hidden" name="totalPrice" value="<%=total%>">
	</form>

</body>
</html>