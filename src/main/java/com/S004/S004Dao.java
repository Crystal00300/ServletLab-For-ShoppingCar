package com.S004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Crystal
 * login
 * S004 登入
 */
public class S004Dao {
	public List<S004PO> LoginByUser(String username, String password) {
		Connection conn = null;
		
		List<S004PO> s004loginList = new ArrayList<>();

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//61.216.84.220:1534/XE", "demo", "123456");
			System.out.println("連線成功");

			String sql = "SELECT * FROM SHOP_CUSTOMER WHERE USERNAME=? AND PASSWORD=?";
			
			PreparedStatement sptmt = conn.prepareStatement(sql);

			sptmt.setString(1, username);
			sptmt.setString(2, password);
			
//			if (username != null && !"".equals(username)) {
//				sql += " AND USERNAME = '" + username + "'";
//			}
//
//			if (password != null && !"".equals(password)) {
//				sql += " AND PASSWORD = '" + password + "'";
//			}

			ResultSet rs = sptmt.executeQuery();

			while (rs.next()) {
				S004PO login = new S004PO();
				login.setId(rs.getString("ID"));
				login.setUserName(rs.getString("USERNAME"));
				login.setPassWord(rs.getString("PASSWORD"));
				login.setcName(rs.getString("CNAME"));
				login.seteName(rs.getString("ENAME"));

				s004loginList.add(login);
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
		return s004loginList;
	}
}