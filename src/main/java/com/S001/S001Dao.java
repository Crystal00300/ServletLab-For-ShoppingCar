package com.S001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Crystal
 * Index 首頁
 * S001 findAllProduct
 */
public class S001Dao {

	public List<S001PO> findAllProduct() {
		Connection conn = null;
		
		List<S001PO> datalist = new ArrayList<S001PO>();

		try {
			// 建立連線
			Class.forName("oracle.jdbc.OracleDriver");
			// DB連接
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");
			
			// 操作DB，實現增刪查改
			Statement st = conn.createStatement();

			String sql = "SELECT * FROM SHOP_PRODUCTS";
			ResultSet rs = st.executeQuery(sql);
			
			//预编译SQL，减少sql执行 ---> PreparedStatement ptmt = conn.prepareStatement(sql); 
			
			// 把資料庫相對應的欄位資料,放進list
			while (rs.next()) {
				S001PO s001po = new S001PO();
				
				s001po.setProdCode(rs.getString("PROD_CODE"));
				s001po.setProdName(rs.getString("PROD_NAME"));
				s001po.setProdDesc(rs.getString("PROD_DESC"));
				s001po.setProdAmt(rs.getString("PROD_AMT"));
				s001po.setBeginTime(rs.getDate("BEGIN_TIME"));
				s001po.setEndTime(rs.getDate("END_TIME"));
				s001po.setImgLink(rs.getString("IMG_LINK"));

				datalist.add(s001po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return datalist;
	}
}