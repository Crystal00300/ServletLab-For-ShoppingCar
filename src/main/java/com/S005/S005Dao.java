package com.S005;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.S001.S001PO;

/*
 * @author Crystal
 * shopCar
 * (S003結束後) session帶著 prodCode 去資料庫找相對應的資料
 * 裝進新建的List 放回servlet ->jsp (購物車呈現)
 */
public class S005Dao {

	public List<S001PO> findDataToList(List<String> findToDbForList) {

		Connection conn = null;

		String sql = "SELECT * FROM SHOP_PRODUCTS WHERE PROD_CODE=?";

		List<S001PO> prod = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			// 把條件帶入，去資料庫一筆一筆找出
			for (int i = 0; i < findToDbForList.size(); i++) {
				String prodCode = findToDbForList.get(i);
				// 預編譯sql，減少sql執行
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 傳參數
				pstmt.setString(1, prodCode);
				// 執行
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {

					S001PO s001po = new S001PO();
					s001po.setProdName(rs.getString("PROD_NAME"));
					s001po.setProdAmt(rs.getString("PROD_AMT"));
					s001po.setProdCode(rs.getString("PROD_CODE"));

					prod.add(s001po);
				}
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
		return prod;
	}
}