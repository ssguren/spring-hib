package com.his.ws.model.rs;

import com.his.ws.model.DoctorInfo;
import com.his.ws.model.WSResult;

public class DoctorResult extends WSResult {

	private DoctorInfo doctorInfo;

	public DoctorInfo getDoctor() {
		return doctorInfo;
	}

	public void setDoctor(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public DoctorResult() {
		super();
	}

}
