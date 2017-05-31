package com.his.ws.model.rs;

import com.his.ws.model.UserWaitingInfo;
import com.his.ws.model.WSResult;

public class UserWaitingListResult extends WSResult {

	private UserWaitingInfo userWaitingInfo;

	public UserWaitingInfo getUserWaitingInfo() {
		return userWaitingInfo;
	}

	public void setUserWaitingInfo(UserWaitingInfo userWaitingInfo) {
		this.userWaitingInfo = userWaitingInfo;
	}

}
