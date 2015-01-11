package com.taurusandchicken.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taurusandchicken.web.module.User;
import com.taurusandchicken.web.module.UserRole;

@Repository
public class UserDAO {
	@Autowired
	SessionFactory sessionFactory;

	public void addUser(String email, String username, String password) {

		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = new User(email, username, password);
		session.save(user);
		UserRole  userRole = new UserRole(user, "ROLE_SHOPER");
		session.save(userRole);
		trans.commit();
	}
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		System.out.println("findByUserName");
		List<User> users = new ArrayList<User>();
		System.out.println("=====++++++++=======1");
		Session session=sessionFactory.getCurrentSession();
		System.out.println("=====++++++++=======2");
		Transaction trans=session.beginTransaction();
		String hql = "from User where username = '"+username+"'";
		org.hibernate.Query query = session.createQuery(hql);
		users = query.list();
		
		
		System.out.println("=====++++++++=======3");

		User user = users.get(0);
		//User user = new User();

		/*String hql2 = "from UserRole where username = '"+username+"'";
		org.hibernate.Query query2 = session.createQuery(hql2);
		Set<UserRole> foo = new HashSet<UserRole>(query2.list());
		user.setUserRole(foo); 
		
		*/
		trans.commit();
		System.out.println("=====++++++++=======4");


		return user;
	}

	
	/*@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		System.out.println("findByUserName");
		List<User> users = new ArrayList<User>();
		System.out.println("=====++++++++=======1");
		Session session=sessionFactory.getCurrentSession();
		System.out.println("=====++++++++=======2");
		Transaction trans=session.beginTransaction();
		String hql = "from user where username = '"+username+"'";
		org.hibernate.Query query = session.createQuery(hql);
		users = query.list();
		trans.commit();
		System.out.println("=====++++++++=======3");

		User user = users.get(0);
		Transaction trans2=session.beginTransaction();
		String hql2 = "from user_roles where username = '"+username+"'";
		org.hibernate.Query query2 = session.createQuery(hql2);
		user.setUserRole((Set)query.list()); 
		trans.commit();
		System.out.println("=====++++++++=======4");


		return user;
	}
*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}