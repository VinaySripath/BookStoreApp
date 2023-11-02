package com.sprint.bspro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookId;
	private String title;
	private String description;
	private String category;
	private int pages;
	private int price;
	
	@ManyToOne
	private Author author;
	
	@OneToMany(cascade =  CascadeType.ALL)
	private List<Reviews> feedbacks;
	
	@ManyToMany
	@JoinTable(name = "OrderDetails", 
	joinColumns = @JoinColumn(name = "book"), 
	inverseJoinColumns = @JoinColumn(name = "orderNumber"))
	private List<AppOrder> orders;

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the feedbacks
	 */
	public List<Reviews> getFeedbacks() {
		return feedbacks;
	}

	/**
	 * @param feedbacks the feedbacks to set
	 */
	public void setFeedbacks(List<Reviews> feedbacks) {
		this.feedbacks = feedbacks;
	}

	/**
	 * @return the orders
	 */
	public List<AppOrder> getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<AppOrder> orders) {
		this.orders = orders;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	public Book(int bookId, String title, String description, String category, int pages, int price,
			List<Reviews> feedbacks, List<AppOrder> orders) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.description = description;
		this.category = category;
		this.pages = pages;
		this.price = price;
		this.feedbacks = feedbacks;
		this.orders = orders;
	}

	public Book(String title, String description, String category, int pages, int price) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.pages = pages;
		this.price = price;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
