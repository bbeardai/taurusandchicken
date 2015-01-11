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
@Table(name = "address")
public class Address {
	@Id
	@Column(name = "addressid")
	private String addressid;
	
	@Column(name = "line1")
	private String line1;

	@Column(name = "province")
	private String province;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "zip")
	private String zip;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "memo")
	private String memo;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "idphotoid")
	private Idphoto idphoto;
	
	@OneToMany(mappedBy = "address" ,orphanRemoval=true,fetch = FetchType.LAZY)
	private Set<Shiporder> orders = new HashSet<Shiporder>(0);
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Address(String line1,
			String province, String city, String zip, String phone,
			String memo, User user, Idphoto idphoto) {
		super();
		this.addressid = UUID.randomUUID().toString();
		this.line1 = line1;
		this.province = province;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.memo = memo;
		this.user = user;
		this.idphoto = idphoto;
	}



	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}



	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Idphoto getIdphoto() {
		return idphoto;
	}

	public void setIdphoto(Idphoto idphoto) {
		this.idphoto = idphoto;
	}

	public Set<Shiporder> getOrders() {
		return orders;
	}

	public void setOrders(Set<Shiporder> orders) {
		this.orders = orders;
	}
	
	
	
}
