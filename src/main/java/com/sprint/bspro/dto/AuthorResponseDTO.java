package com.sprint.bspro.dto;


public class AuthorResponseDTO {
	private int userCode;
	private String password;
	private String username;
	private String userrole; 
	private String name; 
	private String houseAddress;
	private String city;
	private String country;
	private String email;
	private long phone;
	private String region;
	private String nativeLanguage;
	private String status;
	
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getNativeLanguage() {
		return nativeLanguage;
	}
	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AuthorResponseDTO(int userCode, String password, String username, String userrole, String name,
			String houseAddress, String city, String country, String email, long phone, String region,
			String nativeLanguage, String status) {
		super();
		this.userCode = userCode;
		this.password = password;
		this.username = username;
		this.userrole = userrole;
		this.name = name;
		this.houseAddress = houseAddress;
		this.city = city;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.region = region;
		this.nativeLanguage = nativeLanguage;
		this.status = status;
	}
	public AuthorResponseDTO() {
		super();
	}
	
}
