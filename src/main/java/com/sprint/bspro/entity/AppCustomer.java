package com.sprint.bspro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.criteria.Order;

@Entity
public class AppCustomer extends BookStoreUser{

	private String fullName;
	
	@Embedded
	private ContactInfo contactInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<AppOrder> allPlacedOrders;

	public AppCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppCustomer(int userCode, String username, String password, String userrole) {
		super(userCode, username, password, userrole);
		// TODO Auto-generated constructor stub
	}

	public AppCustomer(String fullName, ContactInfo contactInfo, List<AppOrder> allPlacedOrders) {
		super();
		this.fullName = fullName;
		this.contactInfo = contactInfo;
		this.allPlacedOrders = allPlacedOrders;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * @return the allPlacedOrders
	 */
	public List<AppOrder> getAllPlacedOrders() {
		return allPlacedOrders;
	}

	/**
	 * @param allPlacedOrders the allPlacedOrders to set
	 */
	public void setAllPlacedOrders(List<AppOrder> allPlacedOrders) {
		this.allPlacedOrders = allPlacedOrders;
	}

	

	
	
	

	
	
	
	
}
