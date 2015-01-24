package com.taurusandchicken.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taurusandchicken.web.module.Idphoto;
import com.taurusandchicken.web.module.User;
import com.taurusandchicken.web.module.UserRole;

@Repository
public class IdphotoDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void addUser(Idphoto idphoto) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(idphoto);
		
		trans.commit();
	}
	public void updateIdphoto(Idphoto idphoto) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(idphoto);
		
		trans.commit();
	}
	
	public void deleteIdphoto(Idphoto idphoto) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(idphoto);
		
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Idphoto> findByUserName(String username) {
		List<Idphoto> idphotos = new ArrayList<Idphoto>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Idphoto where username = '"+username+"'";
		org.hibernate.Query query = session.createQuery(hql);
		idphotos = query.list();
		
		trans.commit();

		return idphotos;
	}
	
	
	@SuppressWarnings("unchecked")
	public Idphoto findByIdphotoid(String idphotoid) {
		List<Idphoto> idphotos = new ArrayList<Idphoto>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Idphoto where idphotoid = '"+idphotoid+"'";
		org.hibernate.Query query = session.createQuery(hql);
		idphotos = query.list();
		
		trans.commit();

		if(idphotos.size()==0){
			return null;
		}else{
			return idphotos.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Idphoto findByidnumber(String idnumber) {
		List<Idphoto> idphotos = new ArrayList<Idphoto>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Idphoto where idnumber = '"+idnumber+"'";
		org.hibernate.Query query = session.createQuery(hql);
		idphotos = query.list();
		
		trans.commit();

		if(idphotos.size()==0){
			return null;
		}else{
			return idphotos.get(0);
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public Idphoto findByidnumberPublic(String idnumber) {
		List<Idphoto> idphotos = new ArrayList<Idphoto>();
		Session session=sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		String hql = "from Idphoto where idnumber = '"+idnumber+"' and username = 'public'";
		org.hibernate.Query query = session.createQuery(hql);
		idphotos = query.list();
		
		trans.commit();

		if(idphotos.size()==0){
			return null;
		}else{
			return idphotos.get(0);
		}
	
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}