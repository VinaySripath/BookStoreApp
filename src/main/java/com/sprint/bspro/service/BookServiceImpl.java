package com.sprint.bspro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.dto.AppOrderResponseDTO;
import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.repository.IAuthorRepository;
import com.sprint.bspro.repository.IBookRepository;
import com.sprint.bspro.repository.IReviewsRepository;
import com.sprint.bspro.util.AppOrderDTOMapper;
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
		Book book = bookRepository.getBookByTitle(title);
		return book;
	}
	@Override
	public List<Book> listBooksByCategory(String category) {
		return bookRepository.getBookByCategory(category);
	}
	
	@Override
	@Transactional
	public Book addFeedbacks(String title, int rid) {
		Book book = bookRepository.getBookByTitle(title);
		if(book != null) {
			List<Reviews> reviews = book.getFeedbacks();
			Reviews review = reviewsRepository.findById(rid).get();
			if(review != null) {
				review.setBook(book);
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

	@Override
	public List<Book> listBooksByAuthor(String authorname) {
		Author author = authorRepository.getAuthorByUsername(authorname);
		if(author != null) {
			List<Book> booklist = bookRepository.getBookByAuthor(author);
			return booklist;
		}
		return null;
	}

	@Override
	public List<Book> listBooksBySearch(String key) {
		if(key != null) {
			return bookRepository.findByTitleContaining(key);
		}
		return null;
	}

	@Override
	public List<AppOrder> getAllOrdersByBook(String bookname) {
		Book book = bookRepository.getBookByTitle(bookname);
		if(book != null) {
			return book.getOrders();
		}
		return null;
	}

	@Override
	public List<AppOrder> getAllOrdersByBookCategory(String category) {
		if(category != null) {
			List<Book> books = bookRepository.getBookByCategory(category);
			List<AppOrder> allOrders = new ArrayList<>();
			for(Book book: books) {
				allOrders.addAll(book.getOrders());
			}
			return allOrders;
		}
		return null;
	}

	@Override
	public List<Book> getBookByQuantity(int maxQuantity) {
		return bookRepository.findByAvailableQuantityLessThanEqual(maxQuantity);
	}

}
