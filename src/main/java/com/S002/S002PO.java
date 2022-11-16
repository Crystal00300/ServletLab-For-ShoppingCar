package com.S002;

import java.util.Date;

/*
 * DB:SHOPCAR
 */
public class S002PO {

	private String orderNo;

	/*
	 * 訂單時間
	 */
	private Date orderTime;

	/*
	 * 訂單金額
	 */
	private String OrderTotalAmt;

	/*
	 * 訂單狀態
	 */
	private String orderStatus;

	/*
	 * 訂單客戶
	 */
	private String orderUser;

	/*
	 * REMARK
	 */
	private String remark;

	/*
	 * IS_DEL
	 */
	private String isDel;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderTotalAmt() {
		return OrderTotalAmt;
	}

	public void setOrderTotalAmt(String orderTotalAmt) {
		OrderTotalAmt = orderTotalAmt;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
}