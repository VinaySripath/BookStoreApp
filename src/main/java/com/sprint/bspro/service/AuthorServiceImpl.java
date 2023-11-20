package com.sprint.bspro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.ContactInfo;
import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.repository.IAuthorRepository;
import com.sprint.bspro.repository.IReviewsRepository;
@Service
public class AuthorServiceImpl implements IAuthorService{

	@Autowired
	IAuthorRepository authorRepository;
	@Autowired
	IReviewsRepository reviewsRepository;
	@Autowired
	IBookService bookService;
	@Override
	public Author createAppAuthor(Author author) {
		if(author != null) {
			authorRepository.save(author);
			author.setUserCode(authorRepository.getAuthorByUsername(author.getUsername()).getUserCode());
			return author;
		}
		return null;
	}

	@Override
	@Transactional
	public Author updateAuthor(Author author) {
		if(author != null) {
			int id = author.getUserCode();
			Author savedAuthor = authorRepository.findById(id).get();
			if(savedAuthor != null) {
				if(author.getContactInfo() != null) {
					ContactInfo cinfo = savedAuthor.getContactInfo();
					
					ContactInfo newInfo = author.getContactInfo();
					if(newInfo.getCity()!= null) {
						cinfo.setCity(newInfo.getCity());
					}
					if(newInfo.getCountry()!= null) {
						cinfo.setCountry(newInfo.getCountry());
					}
					if(newInfo.getEmail()!= null) {
						cinfo.setEmail(newInfo.getEmail());
					}
					if(newInfo.getHouseAddress()!= null) {
						cinfo.setHouseAddress(newInfo.getHouseAddress());
					}
					if(newInfo.getPhone()!= 0) {
						cinfo.setPhone(newInfo.getPhone());
					}
					savedAuthor.setContactInfo(cinfo);
				}
				if(author.getName()!= null) {
					savedAuthor.setName(author.getName());
				}
				if(author.getRegion()!= null) {
					savedAuthor.setRegion(author.getRegion());
				}
				if(author.getNativeLanguage()!= null) {
					savedAuthor.setNativeLanguage(author.getNativeLanguage());
				}
				return savedAuthor;
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public Author updateAuthorByName(Author author, String username) {
		if(author != null) {
			Author savedAuthor = authorRepository.getAuthorByUsername(username);
			if(savedAuthor != null) {
				if(author.getContactInfo() != null) {
					ContactInfo cinfo = savedAuthor.getContactInfo();
					
					ContactInfo newInfo = author.getContactInfo();
					if(newInfo.getCity()!= null) {
						cinfo.setCity(newInfo.getCity());
					}
					if(newInfo.getCountry()!= null) {
						cinfo.setCountry(newInfo.getCountry());
					}
					if(newInfo.getEmail()!= null) {
						cinfo.setEmail(newInfo.getEmail());
					}
					if(newInfo.getHouseAddress()!= null) {
						cinfo.setHouseAddress(newInfo.getHouseAddress());
					}
					if(newInfo.getPhone()!= 0) {
						cinfo.setPhone(newInfo.getPhone());
					}
					savedAuthor.setContactInfo(cinfo);
				}
				if(author.getName()!= null) {
					savedAuthor.setName(author.getName());
				}
				if(author.getRegion()!= null) {
					savedAuthor.setRegion(author.getRegion());
				}
				if(author.getNativeLanguage()!= null) {
					savedAuthor.setNativeLanguage(author.getNativeLanguage());
				}
				return savedAuthor;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Author updateAuthorStatus(int usercode, String status) {
		if(status != null && usercode != 0) {
			Author savedAuthor = authorRepository.findById(usercode).get();
			if(savedAuthor != null) {
				savedAuthor.setStatus(status);
				return savedAuthor;
			}
		}
		return null;
	}
	
	@Override
	public Author viewAuthor(int usercode) {
		return authorRepository.findById(usercode).get();
	}
	
	@Override
	public Author viewAuthorByName(String username) {
		return authorRepository.getAuthorByUsername(username);
	}

	@Override
	@Transactional
	public Author addFeedbacks(String username, int rid) {
		Author a = authorRepository.getAuthorByUsername(username);
		if(a != null) {
			List<Reviews> reviews = a.getFeedbacks();
			Reviews review = reviewsRepository.findById(rid).get();
			if(review != null) {
				review.setAuthor(a);
				reviews.add(review);
				a.setFeedbacks(reviews);
				return a;
			}
		}
		return null;
	}

	@Override
	public List<AppOrder> getAllOrdersByAuthor(String username) {
		if(username != null) {
			List<AppOrder> allOrders= new ArrayList<>();
			List<Book> books = bookService.listBooksByAuthor(username);
			for(Book book: books) {
				List<AppOrder> allBookOrders = bookService.getAllOrdersByBook(book.getTitle());
				allOrders.addAll(allBookOrders);
			}
			return allOrders;
		}
		return null;
	}

	@Override
	public List<Author> viewAllAuthor() {
		return authorRepository.findAll();
	}

	@Override
	public List<Author> viewAllAuthorByStatus(String status) {
		return authorRepository.findByStatus(status);
	}

}
