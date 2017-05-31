package com.his.ws.model.rs;

import com.his.ws.model.DepartmentInfo;
import com.his.ws.model.WSResult;

public class DepartmentResult extends WSResult {
	private DepartmentInfo departmentInfo;

	public DepartmentResult() {
		super();
	}

	public DepartmentInfo getDepartment() {
		return departmentInfo;
	}

	public void setDepartment(DepartmentInfo departmentInfo) {
		this.departmentInfo = departmentInfo;
	}

}
