package com.sprint.bspro.util;

import com.sprint.bspro.dto.ReviewsRequestDTO;
import com.sprint.bspro.dto.ReviewsResponseDTO;
import com.sprint.bspro.entity.Reviews;

public class ReviewsDTOMapper {
	public ReviewsResponseDTO getReviewsDTOFromReviews (Reviews review ) {
		if(review != null) {
			ReviewsResponseDTO reviewResponse = new ReviewsResponseDTO();
			reviewResponse.setComment(review.getComment());
			reviewResponse.setCustomerName(review.getCustomerName());
			reviewResponse.setRatings(review.getRatings());
			reviewResponse.setReviewCategory(review.getReviewCategory());
			reviewResponse.setReviewDate(review.getReviewDate());
			reviewResponse.setRid(review.getRid());
			return reviewResponse;
		}
		return null;
	}
	public Reviews getReviewsFromReviewsDTO (ReviewsRequestDTO reviewDTO) {
		if(reviewDTO != null) {
			Reviews review = new Reviews();
			review.setComment(reviewDTO.getComment());
			review.setCustomerName(reviewDTO.getCustomerName());
			review.setRatings(reviewDTO.getRatings());
			review.setReviewCategory(reviewDTO.getReviewCategory());
			review.setRid(reviewDTO.getRid());
			return review;
		}
		return null;
	}
}
