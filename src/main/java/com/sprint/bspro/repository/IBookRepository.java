package com.sprint.bspro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {
	public Book getBookByTitle(String title);
	public List<Book> getBookByCategory(String category);
	public List<Book> getBookByAuthor(Author author);
	public List<Book> findByTitleContaining(String title);
	public List<Book> findByAvailableQuantityLessThanEqual(int maxQuantity);
}
