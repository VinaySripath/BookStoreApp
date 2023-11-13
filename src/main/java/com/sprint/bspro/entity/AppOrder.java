package com.sprint.bspro.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

import io.swagger.annotations.Api;

@Api(value = "All AppOrder Specific AppOrder Endpoints")
@Entity
public class AppOrder {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int orderNumber;
	private String orderDate;
	private int orderValue;
	private String orderStatus;
	
//	@ManyToMany
//	@JoinTable(name = "OrderDetails", 
//	joinColumns = @JoinColumn(name = "orderNumber"), 
//	inverseJoinColumns = @JoinColumn(name = "book"))
//	private List<Book> orderedBooks;
	@ElementCollection
	 @CollectionTable(name = "order_item_mapping", 
    joinColumns = {@JoinColumn(name="OrderNumber",referencedColumnName = "orderNumber")})
	@MapKeyColumn(name = "BookNumber")
  @Column(name = "Units")
	Map<Book, Integer> orderDetails = new HashMap<>();
	
	@ManyToOne
	private AppCustomer customer;

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public AppCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(AppCustomer customer) {
		this.customer = customer;
	}
	
	public Map<Book, Integer> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Map<Book, Integer> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public AppOrder(String orderDate, int orderValue, String orderStatus, Map<Book, Integer> orderDetails,
			AppCustomer customer) {
		super();
		this.orderDate = orderDate;
		this.orderValue = orderValue;
		this.orderStatus = orderStatus;
		this.orderDetails = orderDetails;
		this.customer = customer;
	}

	public AppOrder(int orderNumber, String orderDate, int orderValue, String orderStatus) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderValue = orderValue;
		this.orderStatus = orderStatus;
	}

	public AppOrder() {
		super();
	}
	
	
	
}
