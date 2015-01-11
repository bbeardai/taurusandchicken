package com.taurusandchicken.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.taurusandchicken.web.dao.UserDAO;
import com.taurusandchicken.web.module.User;
@Service
public class Test {
	UserDAO userDAO = new UserDAO();
	
	@org.junit.Test
	public void nkafjv(){
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByUserName("q");
		System.out.println(user.getnickname());
		System.out.println(user.getUsername());
		System.out.println(user.getRdate());
		System.out.println(user.getPassword());
		System.out.println(user.getUserRole());
		System.out.println();
		System.out.println();
		
	}
	
}
