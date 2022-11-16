package com.S003;

/*
 * DB：SHOPCAR_DTL
 */

public class S003PO {

	private String orderNo;

	/*
	 * 訂單序號
	 */
	private String orderSeq;

	/*
	 * 訂單編號
	 */
	private String orderProdCode;

	/*
	 * 商品名稱
	 */
	private String orderProdName;

	/*
	 * 商品價格
	 */
	private String orderProdAmt;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getOrderProdCode() {
		return orderProdCode;
	}

	public void setOrderProdCode(String orderProdCode) {
		this.orderProdCode = orderProdCode;
	}

	public String getOrderProdName() {
		return orderProdName;
	}

	public void setOrderProdName(String orderProdName) {
		this.orderProdName = orderProdName;
	}

	public String getOrderProdAmt() {
		return orderProdAmt;
	}

	public void setOrderProdAmt(String orderProdAmt) {
		this.orderProdAmt = orderProdAmt;
	}
}