package com.taurusandchicken.web.module;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shiporder")
public class Shiporder {
	@Id
	@Column(name = "shiporderid")
	private String shiporderid;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "tracking")
	private String tracking;
	
	@Column(name = "paydate")
	private String paydate;
	
	@Column(name = "taobaoid")
	private String taobaoid;
	
	@Column(name = "shopname")
	private String shopname;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "addressid")
	private Address address;
	
	@OneToMany(mappedBy = "shiporder" ,orphanRemoval=true,fetch = FetchType.EAGER)
	private Set<Orderitem> orderitems = new HashSet<Orderitem>(0);
	
	

	public Shiporder() {
		// TODO Auto-generated constructor stub
	}
	
	public Shiporder(String shiporderid, User user, Address address) {
		super();
		this.shiporderid = shiporderid;
		this.status = "New";
		this.user = user;
		this.address = address;
	}
	
	
	

	

	public Shiporder(String shiporderid, String paydate,
			String taobaoid, Address address,String shopname) {
		super();
		this.shiporderid = shiporderid;
		this.status = "未上传身份证";
		this.paydate = paydate;
		this.taobaoid = taobaoid;
		this.address = address;
		this.shopname = shopname;
	}

	public String getShiporderid() {
		return shiporderid;
	}

	public void setShiporderid(String shiporderid) {
		this.shiporderid = shiporderid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public String getTaobaoid() {
		return taobaoid;
	}

	public void setTaobaoid(String taobaoid) {
		this.taobaoid = taobaoid;
	}

	public Set<Orderitem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
	

}
