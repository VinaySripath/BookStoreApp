package com.sprint.bspro.dto;

public class BookResponseDTO {
	private String title;
	private String description;
	private int price;
	private int pages;
	private String category;
	private int id;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BookResponseDTO(String title, String description, int price, int pages, String category, int id) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.pages = pages;
		this.category = category;
		this.id = id;
	}
	public BookResponseDTO() {
		super();
	}
	
}
