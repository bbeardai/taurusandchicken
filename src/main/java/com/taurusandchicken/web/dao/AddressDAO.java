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
import com.taurusandchicken.web.module.User;
import com.taurusandchicken.web.module.UserRole;

@Repository
public class AddressDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void addAddress(Address address) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(address);
		
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Address> findByUserName(String username) {
		List<Address> addresses = new ArrayList<Address>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Address where username = '"+username+"'";
		org.hibernate.Query query = session.createQuery(hql);
		addresses = query.list();
		
		trans.commit();

		return addresses;
	}
	
	@SuppressWarnings("unchecked")
	public Address findById(String id) {
		List<Address> addresses = new ArrayList<Address>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Address where addressid = '"+id+"'";
		org.hibernate.Query query = session.createQuery(hql);
		addresses = query.list();
		
		trans.commit();

		return addresses.get(0);
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}