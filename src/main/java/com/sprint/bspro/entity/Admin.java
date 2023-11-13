package com.sprint.bspro.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;
@ApiModel(description = "Details about Admin Bean")
@Entity
public class Admin extends BookStoreUser {
	
	private String fullName;
	
	@Embedded
	private ContactInfo contactInfo;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

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
	}

	public Admin(String username, String password, String userrole) {
		super( username, password, userrole);
	}
	
	public Admin(String username, String password, String userrole, String fullName, ContactInfo contactInfo) {
		super( username, password, userrole);
		this.fullName = fullName;
		this.contactInfo = contactInfo;
	}
	
}
