package com.his.service.impl;

import com.his.dao.IBaseDAO;
import com.his.service.ILoginService;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LoginService implements ILoginService {
	
	@ManyToOne
	private IBaseDAO dao;

	public String login(String username, String password, String aucode) {
		//TODO 
		return dao.login(username, password);
	}

	public IBaseDAO getDao() {
		return dao;
	}

	public void setDao(IBaseDAO dao) {
		this.dao = dao;
	}
	
}
