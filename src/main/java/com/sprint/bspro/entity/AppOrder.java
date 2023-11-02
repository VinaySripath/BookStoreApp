package com.sprint.bspro.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class AppOrder {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int orderNumber;
	private String orderDate;
	private int orderValue;
	private String orderStatus;
	@ManyToOne
	private AppOrder orders;
	
	@ManyToMany
	@JoinTable(name = "OrderDetails", 
	joinColumns = @JoinColumn(name = "orderNumber"), 
	inverseJoinColumns = @JoinColumn(name = "book"))
	private List<Book> orderedBooks;

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<Book> getOrderedBooks() {
		return orderedBooks;
	}

	public void setOrderedBooks(List<Book> orderedBooks) {
		this.orderedBooks = orderedBooks;
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

	public AppOrder(int orderNumber, String orderDate, int orderValue, String orderStatus, List<Book> orderedBooks) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderValue = orderValue;
		this.orderStatus = orderStatus;
		this.orderedBooks = orderedBooks;
	}

	public AppOrder() {
		super();
	}
	
	
	
}
