package com.taurusandchicken.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taurusandchicken.web.module.Address;
import com.taurusandchicken.web.module.Idphoto;
import com.taurusandchicken.web.module.Shiporder;
import com.taurusandchicken.web.module.User;
import com.taurusandchicken.web.module.UserRole;

@Repository
public class OrderDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void addOrder(Shiporder order) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(order);
		
		trans.commit();
	}
	public void updateOrder(Shiporder order) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(order);
		
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Shiporder> findByUserName(String username) {
		List<Shiporder> orders = new ArrayList<Shiporder>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Shiporder where username = '"+username+"'";
		org.hibernate.Query query = session.createQuery(hql);
		orders = query.list();
		
		trans.commit();

		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public List<Shiporder> findByCS(String username) {
		List<Shiporder> orders = new ArrayList<Shiporder>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Shiporder where cs = '"+username+"' and status<5";
		org.hibernate.Query query = session.createQuery(hql);
		orders = query.list();
		
		trans.commit();

		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public Shiporder findById(String id) {
		List<Shiporder> orders = new ArrayList<Shiporder>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Shiporder where shiporderid = '"+id+"'";
		org.hibernate.Query query = session.createQuery(hql);
		orders = query.list();
		
		trans.commit();
		if(orders.size()>0){
			return orders.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Shiporder> Allorder() {
		List<Shiporder> orders = new ArrayList<Shiporder>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Shiporder";
		org.hibernate.Query query = session.createQuery(hql);
		orders = query.list();
		
		trans.commit();

		return orders;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}