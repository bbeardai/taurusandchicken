package com.taurusandchicken.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taurusandchicken.web.module.UserRole;

@Repository
public class UserRoleDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	private SessionFactory getCurrentSession() {
        return (SessionFactory) sessionFactory.getCurrentSession();
    }
 
    public UserRole getRole(String email) {
    	UserRole role = (UserRole) ((Session) getCurrentSession()).load(UserRole.class, email);
        return role;
    }
}
