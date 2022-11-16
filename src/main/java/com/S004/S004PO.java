package com.S004;

/*
 * login
 * DB：SHOP_CUSTOMER
 */
public class S004PO {

	/*
	 * ID
	 */
	private String id;

	/*
	 * 使用者名稱
	 */
	private String userName;

	/*
	 * 使用者密碼
	 */
	private String passWord;

	/*
	 * CNAME
	 */
	private String cName;

	/*
	 * ENAME
	 */
	private String eName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}
}