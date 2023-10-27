package com.sprint.bspro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Author extends BookStoreUser{
	
	private String name;
	private String region;
	private String nativeLanguage;
	
	@Embedded
	private ContactInfo contactInfo;
	@OneToMany(cascade =  CascadeType.ALL)
	private List<Reviews> feedbacks;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Book> books;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the nativeLanguage
	 */
	public String getNativeLanguage() {
		return nativeLanguage;
	}
	/**
	 * @param nativeLanguage the nativeLanguage to set
	 */
	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}
	/**
	 * @return the contactInfo
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	/**
	 * @param contactInfo the contactInfo to set
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
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
	
	public Author(String name, String region, String nativeLanguage, ContactInfo contactInfo, List<Reviews> feedbacks,
			List<Book> books) {
		super();
		this.name = name;
		this.region = region;
		this.nativeLanguage = nativeLanguage;
		this.contactInfo = contactInfo;
		this.feedbacks = feedbacks;
		this.books = books;
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Author(int userCode, String username, String password, String userrole) {
		super(userCode, username, password, userrole);
		// TODO Auto-generated constructor stub
	}
	
}
