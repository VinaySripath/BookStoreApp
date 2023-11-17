package com.sprint.bspro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.Reviews;
@Repository
public interface IReviewsRepository extends JpaRepository<Reviews, Integer> {
	public List<Reviews> getReviewsByReviewCategory(String reviewCategory);
	public List<Reviews> getReviewsByCustomerName(String CustomerName);
	public List<Reviews> getReviewsByBook(Book book);
	public List<Reviews> getReviewsByAuthor(Author author);
}
