package com.sprint.bspro.dto;

import java.util.List;

import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Reviews;

public class BookResponseDTO {
	private String title;
	private String description;
	private int price;
	private int pages;
	private String category;
	private int id;
	private List<ReviewsResponseDTO> reviewList;
	private int availableQuantity;
	private String authorName;
	
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
//	public List<Reviews> getFeedbacks() {
//		return feedbacks;
//	}
//	public void setFeedbacks(List<Reviews> feedbacks) {
//		this.feedbacks = feedbacks;
//	}
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthor(String authorName) {
		this.authorName = authorName;
	}
	public List<ReviewsResponseDTO> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<ReviewsResponseDTO> reviewList) {
		this.reviewList = reviewList;
	}
	public BookResponseDTO(String title, String description, int price, int pages, String category, int id,
			List<ReviewsResponseDTO> reviewList, int availableQuantity, String authorName) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.pages = pages;
		this.category = category;
		this.id = id;
		this.reviewList=reviewList;
		this.availableQuantity = availableQuantity;
		this.authorName=authorName;
	}
	public BookResponseDTO() {
		super();
	}
	
}
