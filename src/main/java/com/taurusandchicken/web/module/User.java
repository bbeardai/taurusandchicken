package com.taurusandchicken.web.module;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
	@Id
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "nickname")
	private String nickname;
	
	
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "rdate")
	private String rdate;
	
	@OneToMany(mappedBy = "user" ,orphanRemoval=true,fetch = FetchType.EAGER)
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	@OneToMany(mappedBy = "user" ,orphanRemoval=true,fetch = FetchType.LAZY)
	private Set<Address> addresses = new HashSet<Address>(0);
	
	@OneToMany(mappedBy = "user" ,orphanRemoval=true,fetch = FetchType.LAZY)
	private Set<Idphoto> idphotos = new HashSet<Idphoto>(0);
	
	@OneToMany(mappedBy = "user" ,orphanRemoval=true,fetch = FetchType.LAZY)
	private Set<Shiporder> orders = new HashSet<Shiporder>(0);
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public User(String username, String nickname, String password) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.enabled = true;
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
		this.rdate = sdf.format(new Date(System.currentTimeMillis()));
		this.username = username;
	}
	
	


	public User(String nickname, String username, String password,
			boolean enabled, Set<UserRole> userRole) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.enabled = false;
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
		this.rdate = sdf.format(new Date(System.currentTimeMillis()));
		this.username = username;
		this.userRole = userRole;
	}




	public String getnickname() {
		return nickname;
	}


	public void setnickname(String nickname) {
		this.nickname = nickname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isenabled() {
		return enabled;
	}


	public void setenabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getRdate() {
		return rdate;
	}


	public void setRdate(String rdate) {
		this.rdate = rdate;
	}


	public Set<UserRole> getUserRole() {
		return userRole;
	}


	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}


	

	
	
	

}
