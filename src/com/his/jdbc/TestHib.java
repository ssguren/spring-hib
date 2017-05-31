package com.his.jdbc;

import java.sql.Connection;

import org.hibernate.Session;

import com.his.HibernateSessionFactory;



public class TestHib {
	public static void main(String[] args) {
		Session session=HibernateSessionFactory.getSession();
		System.out.println(session);
		Connection conn=session.connection();
		System.out.println(conn);
	}
}
