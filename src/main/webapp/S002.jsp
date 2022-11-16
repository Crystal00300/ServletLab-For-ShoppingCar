<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.S002.S002PO"%>
<%@page import="com.S002.S002Servlet"%>
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
	<table>
		<thead>
			<tr>
				<th>訂單編號</th>
				<th>訂單日期</th>
				<th>訂單金額</th>
				<th>訂單狀態</th>
				<th></th>
			</tr>
		</thead>
		<tbody>

			<%
			List<S002PO> s002Order = (List<S002PO>) request.getAttribute("s002FindAll");
			for (int i = 0; i < s002Order.size(); i++) {
			%>

			<tr>
				<td><%=s002Order.get(i).getOrderNo()%></td>
				<td><%=s002Order.get(i).getOrderTime()%></td>
				<td style="text-align: right;"><%=s002Order.get(i).getOrderTotalAmt()%></td>
				<td style="text-align: right;"><%=s002Order.get(i).getOrderStatus()%></td>
				<td><button onclick="location.href='orderDtl?orderDtl=<%=s002Order.get(i).getOrderNo()%>'">訂單明細
					</button>&nbsp;
					<button onclick="location.href='orders?cancelledOrder=<%=s002Order.get(i).getOrderNo()%>'">訂單取消</button></td>
			</tr>

			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>