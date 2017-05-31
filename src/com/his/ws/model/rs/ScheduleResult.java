package com.his.ws.model.rs;

import java.util.List;

import com.his.ws.model.ScheduleInfo;
import com.his.ws.model.WSResult;

public class ScheduleResult extends WSResult {
	private List<ScheduleInfo> scheduleList;

	public List<ScheduleInfo> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleInfo> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public ScheduleResult() {
		super();
	}

}
