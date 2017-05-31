package com.his.ws.model.rs;

import java.util.List;

import com.his.ws.model.DepartmentInfo;
import com.his.ws.model.WSResult;

public class DepartmentsResult extends WSResult {

	private List<DepartmentInfo> departmentList;

	public List<DepartmentInfo> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentInfo> departmentList) {
		this.departmentList = departmentList;
	}

	public DepartmentsResult() {
		super();
	}

}
