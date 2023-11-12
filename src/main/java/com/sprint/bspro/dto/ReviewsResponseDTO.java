package com.sprint.bspro.dto;

public class ReviewsResponseDTO {
	private int rid;
	private String comment;
	private String reviewDate;
	private int ratings;
	private String reviewCategory;
	private String customerName;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getReviewCategory() {
		return reviewCategory;
	}
	public void setReviewCategory(String reviewCategory) {
		this.reviewCategory = reviewCategory;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ReviewsResponseDTO(int rid, String comment, String reviewDate, int ratings, String reviewCategory,
			String customerName) {
		super();
		this.rid = rid;
		this.comment = comment;
		this.reviewDate = reviewDate;
		this.ratings = ratings;
		this.reviewCategory = reviewCategory;
		this.customerName = customerName;
	}
	public ReviewsResponseDTO() {
		super();
	}
}
