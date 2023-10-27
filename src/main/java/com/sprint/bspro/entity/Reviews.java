package com.sprint.bspro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int rid;
	private String comment;
	private String reviewDate; // date only month & year ... like Aug 2023
	private int ratings;
	private String reviewCategory;
	/**
	 * @return the rid
	 */
	public int getRid() {
		return rid;
	}
	/**
	 * @param rid the rid to set
	 */
	public void setRid(int rid) {
		this.rid = rid;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the reviewDate
	 */
	public String getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	/**
	 * @return the ratings
	 */
	public int getRatings() {
		return ratings;
	}
	/**
	 * @param raitings the ratings to set
	 */
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	/**
	 * @return the reviewCategory
	 */
	public String getReviewCategory() {
		return reviewCategory;
	}
	/**
	 * @param reviewCategory the reviewCategory to set
	 */
	public void setReviewCategory(String reviewCategory) {
		this.reviewCategory = reviewCategory;
	}
	public Reviews(int rid, String comment, String reviewDate, int ratings, String reviewCategory) {
		super();
		this.rid = rid;
		this.comment = comment;
		this.reviewDate = reviewDate;
		this.ratings = ratings;
		this.reviewCategory = reviewCategory;
	}
	public Reviews() {
		super();
	}
	
}
