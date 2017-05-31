package com.datacenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.datacenter.pager.PageCounter;
import com.sun.rowset.CachedRowSetImpl;
import com.utils.MiscUtil;
import com.web.listener.StartUpListener;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * Data access object (DAO) for domain model
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseHibernateDAO<T extends Comparable<T>> extends
		HibernateDaoSupport {

	protected final Logger log = Logger.getLogger(getClass());

	protected PageCounter pageCounter = null;

	protected String classType;

	protected String hql_property;

	protected String hql_count;

	protected String hql_select;

	private static DataSource ds = null;

	public BaseHibernateDAO(String classType) {
		this.classType = classType;
		this.hql_property = "from " + classType + " as model where model.";
		this.hql_count = "select count(*) from " + classType;
		this.hql_select = "from " + classType;
	}

	public boolean save(T instance) {
		try {
			getHibernateTemplate().save(instance);

			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public boolean update(T instance) {
		try {
			getHibernateTemplate().update(instance);

			return true;
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public boolean delete(T instance) {
		try {
			getHibernateTemplate().delete(instance);

			return true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public T merge(T instance) {
		try {
			T result = (T) getHibernateTemplate().merge(instance);

			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public boolean attachDirty(T instance) {
		try {
			getHibernateTemplate().saveOrUpdate(instance);

			return true;
		} catch (RuntimeException re) {
			log.error("attachDirty failed", re);
			throw re;
		}
	}

	public boolean attachClean(T instance) {
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);

			return true;
		} catch (RuntimeException re) {
			log.error("attachClean failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) {
		try {
			String queryString = hql_property + propertyName + "= ?";
			List<T> res = getCacheHibernateTemplate().find(queryString, value);

			if (res != null)
				Collections.sort(res);

			return res;
		} catch (RuntimeException re) {
			log.error("find by property failed: " + propertyName + "=" + value);
			log.error(re);

			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		try {
			List<T> res = getCacheHibernateTemplate().find(hql_select);

			if (res != null)
				Collections.sort(res);

			return res;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Integer count() {
		Query query = this.getCurrentSession().createQuery(hql_count);
		return ((Long) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByPage(int pageNum) {
		if (null == this.pageCounter) {
			this.pageCounter = new PageCounter(this.count());
		} else {
			this.pageCounter.refresh(this.count());
		}
		this.pageCounter.pageTo(pageNum);
		Query query = this.getCurrentSession().createQuery(hql_select);
		query.setFirstResult(this.pageCounter.getCurrentItem());
		query.setMaxResults(this.pageCounter.getPageSize());

		return query.list();
	}

	public ResultSet query(String sql, Object[] params) {
		try {
			CachedRowSetImpl rowset = new CachedRowSetImpl();
			Connection conn = getConnection();

			PreparedStatement pre = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				pre.setObject(i + 1, params[i]);

			ResultSet rs = pre.executeQuery();
			rowset.populate(rs);

			rs.close();
			pre.close();
			releaseConnection(conn);

			return rowset;
		} catch (SQLException e) {
			log.error(MiscUtil.traceInfo(e));
		}

		return null;
	}

	public Session getCurrentSession() {
		return super.getSession(true);
	}

	protected HibernateTemplate getCacheHibernateTemplate() {
		getHibernateTemplate().setCacheQueries(true);
		return getHibernateTemplate();
	}

	protected static Connection getConnection() {
		if (ds == null)
			ds = (DataSource) StartUpListener.getBean("dataSource");

		return ds == null ? null : DataSourceUtils.getConnection(ds);
	}

	protected static void releaseConnection(Connection conn) {
		if (conn != null && ds != null) {
			DataSourceUtils.releaseConnection(conn, ds);
		}
	}
}