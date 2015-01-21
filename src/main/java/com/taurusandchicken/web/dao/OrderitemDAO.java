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
import com.taurusandchicken.web.module.Orderitem;
import com.taurusandchicken.web.module.Shiporder;
import com.taurusandchicken.web.module.User;
import com.taurusandchicken.web.module.UserRole;

@Repository
public class OrderitemDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void addOrderitem(Orderitem orderitem) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(orderitem);
		
		trans.commit();
	}
	
	
}