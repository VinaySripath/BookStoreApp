package com.sprint.bspro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Reviews;
@Service
public interface IReviewsService {
	public List<Reviews> listAllReviews();
	public Reviews addReview(Reviews review);
	public Reviews deleteReview(Reviews review);
	public Reviews updateReview(Reviews review);
	public Reviews viewReview(Reviews review);
}
