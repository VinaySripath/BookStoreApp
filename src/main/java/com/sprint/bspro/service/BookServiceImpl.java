package com.sprint.bspro.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Book;
import com.sprint.bspro.repository.IBookRepository;
@Service
public class BookServiceImpl implements IBookService{

	@Autowired
	IBookRepository bookRepository;
	@Override
	public Book createBook(Book b) {
		if(b != null) {
			bookRepository.save(b);
			return b;
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
	public List<Book> listBooksByCategory(String cat) {
		return null;
	}

}
