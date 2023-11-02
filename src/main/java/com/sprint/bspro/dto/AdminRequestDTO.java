package com.sprint.bspro.dto;

public class AdminRequestDTO {
	private int userCode;
	private String username; 
	private String password; 
	private String userrole; 
	private String fullName; 
	private String houseAddress;
	private String city;
	private String country;
	private String email;
	private long phone;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	/**
	 * @return the userCode
	 */
	public int getUserCode() {
		return userCode;
	}
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public AdminRequestDTO(int userCode, String username, String password, String userrole, String fullName,
			String houseAddress, String city, String country, String email, long phone) {
		super();
		this.userCode = userCode;
		this.username = username;
		this.password = password;
		this.userrole = userrole;
		this.fullName = fullName;
		this.houseAddress = houseAddress;
		this.city = city;
		this.country = country;
		this.email = email;
		this.phone = phone;
	}
	public AdminRequestDTO(String username, String password, String userrole, String fullName,
			String houseAddress, String city, String country, String email, long phone) {
		super();
		this.username = username;
		this.password = password;
		this.userrole = userrole;
		this.fullName = fullName;
		this.houseAddress = houseAddress;
		this.city = city;
		this.country = country;
		this.email = email;
		this.phone = phone;
	}
	public AdminRequestDTO() {
		super();
	}
}
