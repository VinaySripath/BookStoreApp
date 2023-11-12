package com.sprint.bspro.dto;

import java.util.List;


public class AppOrderRequestDTO {
	private int orderValue;
	private List<CartDTO> orderDetails;
	private String username;
	
	public int getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}
	public List<CartDTO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<CartDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
