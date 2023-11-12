package com.sprint.bspro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.repository.IReviewsRepository;
@Service
public class ReviewsServiceImpl implements IReviewsService {
	@Autowired
	IReviewsRepository reviewsRepository;
	@Override
	public List<Reviews> listAllReviews() {
		return reviewsRepository.findAll();
	}
	
	@Override
	public List<Reviews> listAllReviewsByCategory(String reviewCategory) {
		if(reviewCategory != null) {
			return reviewsRepository.getReviewsByReviewCategory(reviewCategory);
		}
		return null;
	}

	@Override
	public Reviews addReview(Reviews review) {
		if(review != null) {
			review.setReviewDate(LocalDate.now().toString());
			return reviewsRepository.save(review);
		}
		return null;
	}

	@Override
	public Reviews updateReview(Reviews review) {
		Reviews savedReview = reviewsRepository.findById(review.getRid()).get();
		if(savedReview != null) {
			if(review.getRatings()!= 0) {
				savedReview.setRatings(review.getRatings());
				reviewsRepository.save(savedReview);
				return savedReview;
			}
		}
		return null;
	}

	@Override
	public Reviews viewReview(int rid) {
		if(rid != 0) {
			return reviewsRepository.findById(rid).get();
		}
		return null;
	}

	@Override
	public List<Reviews> listAllReviewsByCustomer(String customerName) {
		if(customerName != null) {
			return reviewsRepository.getReviewsByCustomerName(customerName);
		}
		return null;
	}
	
}
