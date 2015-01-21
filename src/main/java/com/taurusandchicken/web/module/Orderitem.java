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
@Table(name = "orderitem")
public class Orderitem {
	@Id
	@Column(name = "orderitemid")
	private String orderitemid;
	
	@Column(name = "productid")
	private String productid;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "size")
	private String size;
	
	@ManyToOne
	@JoinColumn(name = "shiporderid")
	private Shiporder shiporder;
	
	public Orderitem() {
		// TODO Auto-generated constructor stub
	}

	public Orderitem(String productid, int quantity,
			String size, Shiporder shiporder) {
		super();
		this.orderitemid = UUID.randomUUID().toString();
		this.productid = productid;
		this.quantity = quantity;
		this.size = size;
		this.shiporder = shiporder;
	}

	public String getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(String orderitemid) {
		this.orderitemid = orderitemid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Shiporder getShiporder() {
		return shiporder;
	}

	public void setShiporder(Shiporder shiporder) {
		this.shiporder = shiporder;
	}
	
	

}
