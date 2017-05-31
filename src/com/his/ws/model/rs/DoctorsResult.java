package com.his.ws.model.rs;

import java.util.List;

import com.his.ws.model.DoctorInfo;
import com.his.ws.model.WSResult;

public class DoctorsResult extends WSResult {

	private List<DoctorInfo> doctorList;

	public List<DoctorInfo> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<DoctorInfo> doctorList) {
		this.doctorList = doctorList;
	}

	public DoctorsResult() {
		super();
	}

}
