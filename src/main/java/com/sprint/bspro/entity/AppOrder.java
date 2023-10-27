package com.sprint.bspro.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AppOrder {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int orderNumber;
	private String orderDate;
	private int orderValue;
	private String orderStatus;
	
	@ManyToMany
	@JoinTable(name = "OrderDetails", 
	joinColumns = @JoinColumn(name = "orderNumber"), 
	inverseJoinColumns = @JoinColumn(name = "book"))
	private List<Book> orderedBooks;

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the orderedBooks
	 */
	public List<Book> getOrderedBooks() {
		return orderedBooks;
	}

	/**
	 * @param orderedBooks the orderedBooks to set
	 */
	public void setOrderedBooks(List<Book> orderedBooks) {
		this.orderedBooks = orderedBooks;
	}
	
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderValue
	 */
	public int getOrderValue() {
		return orderValue;
	}

	/**
	 * @param orderValue the orderValue to set
	 */
	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
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
