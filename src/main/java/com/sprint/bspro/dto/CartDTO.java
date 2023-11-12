package com.sprint.bspro.dto;

public class CartDTO {
	private String bookName;
	private int units;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public CartDTO(String bookName, int units) {
		super();
		this.bookName = bookName;
		this.units = units;
	}
	public CartDTO() {
		super();
	}
	
}
