package com.taurusandchicken.web.service;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taurusandchicken.web.dao.UserDAO;
import com.taurusandchicken.web.module.UserRole;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		com.taurusandchicken.web.module.User user = userDao.findByUserName(username);
		System.out.println(user.getUserRole()+"000000000000");
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		System.out.println(username);
		System.out.println(user.getUsername());
		
		System.out.println(user.getPassword());
		return buildUserForAuthentication(user, authorities);
		
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.taurusandchicken.web.module.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isenabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		System.out.println("buildUserAuthority");
		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		System.out.println(Result+"========");
		return Result;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	

}