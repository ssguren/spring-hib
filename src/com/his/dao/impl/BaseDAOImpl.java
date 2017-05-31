package com.his.dao.impl;

import java.sql.Types;

import javax.persistence.Entity;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import com.his.dao.IBaseDAO;

@Entity
public class BaseDAOImpl implements IBaseDAO {

	static Logger log = LogManager.getLogger(BaseDAOImpl.class);

	private DataSource dataSource;

	public String login(String username, String password) {

		String sql = "select count(1) from t_user where name =? and password=?";
		SqlFunction sf = new SqlFunction(getDataSource(), sql);
		sf.declareParameter(new SqlParameter(Types.VARCHAR));
		sf.declareParameter(new SqlParameter(Types.VARCHAR));
		sf.compile();
		int i = sf.run(new Object[] { username, password });
		System.out.println("username:" + username + ",password:" + password);
		if (i >= 1) {
			return username + " login success";
		}
		return username + "login false";
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
