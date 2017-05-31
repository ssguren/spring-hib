package com.his.ws.model.rs;

import com.his.ws.model.WSResult;

public class OrderInfo extends WSResult {
	private String orderNum;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}
