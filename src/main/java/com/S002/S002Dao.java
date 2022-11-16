package com.S002;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.S001.S001PO;
import com.S003.S003PO;

/*
 * @author Crystal
 * orders
 * S002 訂單查詢 (在購物車內已被送出的訂單)
 */
public class S002Dao {

	public String addOrder(int totalPrice) {

		Connection conn = null;
		String orderNum = null;
		
		String sql = "INSERT INTO SHOPCAR\r\n" + "(ORDER_NO, ORDER_TIME, ORDER_TOTAL_AMT, ORDER_STATUS)\r\n"
				+ "VALUES(?, TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), ?, ?)";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			PreparedStatement pstmt = conn.prepareStatement(sql);

			/*
			 * order
			 */

			// 當前時間
			Date nowTime = new Date(System.currentTimeMillis());
			SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdFormatter.format(nowTime);

			// 轉換格式做成編號
			SimpleDateFormat sdFormatterNum = new SimpleDateFormat("yyMMddHHmmss");
			String nowNum = sdFormatterNum.format(nowTime);
			orderNum = "S" + nowNum;

			pstmt.setString(1, orderNum);
			pstmt.setString(2, now);
			pstmt.setInt(3, totalPrice);
			pstmt.setString(4, "成立");
			
			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return orderNum;
	}

	/*
	 * @author Crystal 
	 * orders_DTL 
	 * S002 訂單查詢-訂單明細 (在購物車內已被送出的訂單)
	 */
	public void orderDtl(List<S001PO> productList, String orderNum, int totalPrice) {
		Connection conn = null;

		String sql = "INSERT INTO SHOPCAR_DTL\r\n"
				+ "(ORDER_NO, ORDER_SEQ, ORDER_PROD_CODE, ORDER_PROD_NAME, ORDER_PROD_AMT)\r\n"
				+ "VALUES(?, ?, ?, ?, ?)";

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			PreparedStatement pstmt = conn.prepareStatement(sql);

			System.out.println("productList:" + productList);
			System.out.println("orderNum" + orderNum);
			System.err.println("totalPrice:" + totalPrice);

			/*
			 * order_DTL
			 */
			int orderSeq = 1;
			for (int i = 0; i < productList.size(); i++) {
				S001PO s002ProdList = productList.get(i);

				pstmt.setString(1, orderNum);
				pstmt.setInt(2, orderSeq++);
				pstmt.setString(3, s002ProdList.getProdCode());
				pstmt.setString(4, s002ProdList.getProdName());
				pstmt.setInt(5, Integer.valueOf(s002ProdList.getProdAmt()));
				pstmt.execute();
				System.out.println("Test-pstmt:" + pstmt);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * @author Crystal 
	 * orders_DTL 
	 * S002 訂單查詢-訂單明細 
	 * find Order_DTL
	 */
	public List<S003PO> findOrderDtl(String orderNum) {
		Connection conn = null;

		List<S003PO> orderDtlList = new ArrayList<>();

		String sql = "SELECT ORDER_NO, ORDER_SEQ, ORDER_PROD_CODE, ORDER_PROD_NAME, ORDER_PROD_AMT\r\n"
				+ "FROM SHOPCAR_DTL WHERE ORDER_NO=?";

		System.out.println("sql:" + orderNum);

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, orderNum);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				S003PO orderDtl = new S003PO();

				// 資料庫名稱一樣
				orderDtl.setOrderNo(rs.getString("ORDER_NO"));
				orderDtl.setOrderSeq(rs.getString("ORDER_SEQ"));
				orderDtl.setOrderProdCode(rs.getString("ORDER_PROD_CODE"));
				orderDtl.setOrderProdName(rs.getString("ORDER_PROD_NAME"));
				orderDtl.setOrderProdAmt(rs.getString("ORDER_PROD_AMT"));
				System.out.println("orderDtl:" + orderDtl.getOrderProdName());
				System.out.println("orderDtl:" + orderDtl.getOrderProdAmt());

				orderDtlList.add(orderDtl);
				System.out.println("find Order_DTL:" + orderDtlList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return orderDtlList;
	}

	public List<S002PO> findAll() {
		Connection conn = null;
		List<S002PO> orderList = new ArrayList<S002PO>();

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			Statement st = conn.createStatement();
			/*
			 * ASC: 舊到新(time)、正向操作(A~Z) 
			 * DESC: 新到舊(time)、反向操做(Z~A)
			 */
			
			// sql:訂單時間、訂單金額不為NULL，並且最新的訂單在最上方
			ResultSet rs = st.executeQuery(
					"SELECT ORDER_NO, ORDER_TIME, ORDER_TOTAL_AMT, ORDER_STATUS, ORDER_USER, REMARK, IS_DEL\r\n"
							+ "FROM DEMO.SHOPCAR WHERE  ORDER_TIME IS NOT NULL  AND  ORDER_TOTAL_AMT IS NOT NULL ORDER BY ORDER_TIME DESC");

			while (rs.next()) {
				S002PO order = new S002PO();

				// 資料庫名稱一樣
				order.setOrderNo(rs.getString("ORDER_NO"));
				order.setIsDel(rs.getString("IS_DEL"));
				order.setOrderStatus(rs.getString("ORDER_STATUS"));
				order.setOrderTime(rs.getDate("ORDER_TIME"));
				order.setOrderTotalAmt(rs.getString("ORDER_TOTAL_AMT"));
				order.setOrderUser(rs.getString("ORDER_USER"));

				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("資料筆數:" + orderList.size());
		return orderList;
	}

	/*
	 * 訂單取消 不成立
	 */
	public void updateOrder(String cancelledOrder) {
		Connection conn = null;

		String sql = "UPDATE SHOPCAR SET ORDER_STATUS=? WHERE ORDER_NO=?";

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "不成立");
			pstmt.setString(2, cancelledOrder);

			pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}