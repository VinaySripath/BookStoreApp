package com.sprint.bspro.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.repository.IAuthorRepository;
import com.sprint.bspro.repository.IBookRepository;
import com.sprint.bspro.repository.IReviewsRepository;
@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	IBookRepository bookRepository;
	@Autowired
	IReviewsRepository reviewsRepository;
	@Autowired
	IAuthorRepository authorRepository;
	@Override
	public Book createBook(Book b, String aname) {
		if(b != null && aname != null) {
			Author author = authorRepository.getAuthorByUsername(aname);
			List<Book> books = author.getBooks();
			b.setAuthor(author);
			Book savedBook = bookRepository.save(b);
			books.add(b);
			author.setBooks(books);
			authorRepository.save(author);
			return savedBook;
		}
		return null;
	}

	@Override
	public List<Book> listAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book deleteBook(int bId) {
		try {
			Book b = bookRepository.findById(bId).get();
			bookRepository.deleteById(bId);
			return b;
		}
		catch(Exception e){
		return null;
		}
	}

	@Override
	@Transactional
	public Book editBook(Book b) {
		if(b != null) {
			int id = b.getBookId();
			Book book = bookRepository.findById(id).get();
			if(b.getTitle()!=null) {
				book.setTitle(b.getTitle());
			}
			if(b.getDescription()!= null) {
				book.setDescription(b.getDescription());
			}
			if(b.getPrice()!= 0) {
				book.setPrice(b.getPrice());
			}
			if(b.getFeedbacks()!= null) {
				book.setFeedbacks(b.getFeedbacks());
			}
			if(b.getCategory()!= null) {
				book.setCategory(b.getCategory());
			}
			if(b.getPages()!= 0) {
				book.setPages(b.getPages());
			}
			return book;
		}
		return null;
	}

	@Override
	public Book getBookById(int bId ) {
		return bookRepository.findById(bId).get();
	}
	@Override
	public Book getBookByTitle(String title ) {
		return bookRepository.getBookByTitle(title);
	}
	@Override
	public List<Book> listBooksByCategory(String category) {
		return bookRepository.getBookByCategory(category);
	}
	
	@Override
	@Transactional
	public Book addFeedbacks(String title, int rid) {
		System.out.println("in add feedback to books------ "+title+"  "+rid);
		Book book = bookRepository.getBookByTitle(title);
		if(book != null) {
			List<Reviews> reviews = book.getFeedbacks();
			Reviews review = reviewsRepository.findById(rid).get();
			if(review != null) {
				reviews.add(review);
				book.setFeedbacks(reviews);
				return book;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Book updateAvailableQuantity(String title, int quantity) {
		Book book = bookRepository.getBookByTitle(title);
		if(book != null) {
			book.setAvailableQuantity(quantity);
		}
		return book;
	}

}
