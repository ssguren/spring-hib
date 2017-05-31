package com.his.test;

import javax.persistence.Entity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.his.dao.impl.BaseDAOImpl;

@Entity
public class TestDao extends JdbcDaoSupport{

	public void save(){
		JdbcTemplate jt=new JdbcTemplate(getDataSource());
		jt.update("insert into t_user values ('kk','test','kkeshore')");
	}
}
