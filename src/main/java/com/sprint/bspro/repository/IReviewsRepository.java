package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.Reviews;

public interface IReviewsRepository extends JpaRepository<Reviews, Integer> {

}
