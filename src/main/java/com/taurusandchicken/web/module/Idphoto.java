package com.taurusandchicken.web.module;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "idphoto")
public class Idphoto {
	
	
		@Id
		@Column(name = "idphotoid")
		private String idphotoid;
		
		@Column(name = "path")
		private String path;
		
		@Column(name = "type")
		private String type;
		
		@Column(name = "name")
		private String name;
		
		@ManyToOne
		@JoinColumn(name = "username")
		private User user;
		
		@OneToMany(mappedBy = "idphoto" ,orphanRemoval=true,fetch = FetchType.LAZY)
		private Set<Address> address = new HashSet<Address>(0);
		
		
		public Idphoto() {
			// TODO Auto-generated constructor stub
		}


		public Idphoto(String path, String type, String name, User user) {
			super();
			this.idphotoid = UUID.randomUUID().toString();
			this.path = path;
			this.name = name;
			this.type = type;
			this.user = user;
			
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public String getIdphotoid() {
			return idphotoid;
		}


		public void setIdphotoid(String idphotoid) {
			this.idphotoid = idphotoid;
		}


		public String getPath() {
			return path;
		}


		public void setPath(String path) {
			this.path = path;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public Set<Address> getAddress() {
			return address;
		}


		public void setAddress(Set<Address> address) {
			this.address = address;
		}
		
		

}
