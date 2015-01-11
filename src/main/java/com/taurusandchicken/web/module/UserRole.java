package com.taurusandchicken.web.module;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_roles")
public class UserRole {
	private String userRoleId;
	private User user;
	private String role;

	public UserRole() {
	}

	public UserRole(User user, String role) {
		this.userRoleId= UUID.randomUUID().toString();
		this.user = user;
		this.role = role;
	}

	@Id
	
	@Column(name = "user_role_id", unique = true, nullable = false)
	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne
	@JoinColumn(name = "username")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

