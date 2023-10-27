package com.sprint.bspro.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
@Entity
public class Admin extends BookStoreUser {
	
	private String fullName;
	
	@Embedded
	private ContactInfo contactInfo;

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

	public Admin(String fullName, ContactInfo contactInfo) {
		super();
		this.fullName = fullName;
		this.contactInfo = contactInfo;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int userCode, String username, String password, String userrole) {
		super(userCode, username, password, userrole);
		// TODO Auto-generated constructor stub
	}
	
}
