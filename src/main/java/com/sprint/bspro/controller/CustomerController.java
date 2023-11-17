package com.sprint.bspro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.bspro.dto.AppCustomerRequestDTO;
import com.sprint.bspro.dto.AppCustomerResponseDTO;
import com.sprint.bspro.dto.AppOrderRequestDTO;
import com.sprint.bspro.dto.AppOrderResponseDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.dto.ReviewsRequestDTO;
import com.sprint.bspro.dto.ReviewsResponseDTO;
import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.service.IAppCustomerService;
import com.sprint.bspro.service.IAppOrderService;
import com.sprint.bspro.service.IAuthorService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.service.IReviewsService;
import com.sprint.bspro.util.AppCustomerDTOMapper;
import com.sprint.bspro.util.AppOrderDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;
import com.sprint.bspro.util.ReviewsDTOMapper;
@CrossOrigin
@RestController
@RequestMapping("/user")
@Validated
public class CustomerController {
	@Autowired
	IBookService bookService;
	@Autowired
	IAppCustomerService appCustomerService;
	@Autowired
	IReviewsService reviewService;
	@Autowired
	IAuthorService authorService;
	@Autowired
	IAppOrderService orderService;
	
	@GetMapping("/allbooks")
	public List<BookResponseDTO> getAllBooks(){
		List<Book> books = bookService.listAllBooks();
		List<BookResponseDTO> booksDtos = new ArrayList<>();
		BookDTOMapper brc = new BookDTOMapper();
		for(Book book: books) {
			BookResponseDTO bookDto = brc.getBookDTOFromBook(book);
			booksDtos.add(bookDto);
		}
		return booksDtos;
	}
	
	@GetMapping("/searchbooks")
	public List<BookResponseDTO> getAllBooksBySearch(@RequestParam String key){
		List<Book> books = bookService.listBooksBySearch(key);
		List<BookResponseDTO> booksDtos = new ArrayList<>();
		BookDTOMapper brc = new BookDTOMapper();
		for(Book book: books) {
			BookResponseDTO bookDto = brc.getBookDTOFromBook(book);
			booksDtos.add(bookDto);
		}
		return booksDtos;
	}
	
	@GetMapping("/allbooks/category")
	public List<BookResponseDTO> getAllBooksByCategory(@RequestParam String category){
		List<Book> books = bookService.listBooksByCategory(category);
		List<BookResponseDTO> booksDtos = new ArrayList<>();
		BookDTOMapper brc = new BookDTOMapper();
		for(Book book: books) {
			BookResponseDTO bookDto = brc.getBookDTOFromBook(book);
			booksDtos.add(bookDto);
		}
		return booksDtos;
	}
	
	@GetMapping("/viewbook/author")
	public List<BookResponseDTO> viewBookByAuthor(@RequestParam String authorname) {
		if(authorname!= null) {
			List<Book> listBookByAuthor = bookService.listBooksByAuthor(authorname);
			List<BookResponseDTO> booksDtoList = new ArrayList<>();
			BookDTOMapper dtoConverter = new BookDTOMapper();
			for(Book book: listBookByAuthor) {
				BookResponseDTO converterBook = dtoConverter.getBookDTOFromBook(book);
				booksDtoList.add(converterBook);
			}
			return booksDtoList;
		}
		return null;
	}
	
	@GetMapping("/bookinfo")
	public ResponseEntity<BookResponseDTO> getBookById(@RequestParam int id) {
		 Book book = bookService.getBookById(id);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	
	@GetMapping("/title/data")
	public ResponseEntity<BookResponseDTO> getBookByTitle(@RequestParam String title) {
		 Book book = bookService.getBookByTitle(title);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	
	
	@GetMapping("/viewcustomer")
	public AppCustomerResponseDTO getCustomerByUserCode(@RequestParam int usercode) {
		AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
		return dtoConverter.getAppCustomerDTOFromAppCustomer(appCustomerService.viewCustomer(usercode));
	}
	
	@GetMapping("/viewcustomerbyname")
	public AppCustomerResponseDTO getCustomerByUserCode(@RequestParam String username) {
		AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
		return dtoConverter.getAppCustomerDTOFromAppCustomer(appCustomerService.viewCustomerByUserName(username));
	}
	
	@PutMapping("/updatecustomer")
	public AppCustomerResponseDTO updateCustomer(@Valid @RequestBody AppCustomerRequestDTO customerDTO) {
		if(customerDTO != null) {
			AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
			AppCustomer customer = dtoConverter.getAppCustomerFromAppCustomerDTO(customerDTO);
			AppCustomer savedCustomer = appCustomerService.updateCustomer(customer);
			return dtoConverter.getAppCustomerDTOFromAppCustomer(savedCustomer);
		}
		return null;
	}
	
	@PutMapping("/updatecustomerbyname")
	public AppCustomerResponseDTO updateCustomerByUsername(@Valid @RequestBody AppCustomerRequestDTO customerDTO, @RequestParam String username) {
		if(customerDTO != null) {
			
			AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
			AppCustomer customer = dtoConverter.getAppCustomerFromAppCustomerDTO(customerDTO);
			AppCustomer savedCustomer = appCustomerService.updateCustomerByUsername(customer, username);
			return dtoConverter.getAppCustomerDTOFromAppCustomer(savedCustomer);
		}
		return null;
	}
	
	@PostMapping("/addreview")
	public ReviewsResponseDTO addReview(@Valid @RequestBody ReviewsRequestDTO reviewDTO, @RequestParam String reviewitem) {
		if(reviewDTO != null) {
			ReviewsDTOMapper dtoConverter = new ReviewsDTOMapper();
			Reviews review = dtoConverter.getReviewsFromReviewsDTO(reviewDTO);
			Reviews savedReview = reviewService.addReview(review);
			if(savedReview.getReviewCategory().equals("author")) {
				authorService.addFeedbacks(reviewitem, savedReview.getRid());
			}
			if(savedReview.getReviewCategory().equals("book")) {
				bookService.addFeedbacks(reviewitem, savedReview.getRid());
			}
			return dtoConverter.getReviewsDTOFromReviews(savedReview);
		}
		return null;
	}
	
	@PutMapping("/updatereview")
	public ReviewsResponseDTO updateReview(@Valid @RequestBody ReviewsRequestDTO reviewDTO) {
		if(reviewDTO != null) {
			ReviewsDTOMapper dtoConverter = new ReviewsDTOMapper();
			Reviews review = dtoConverter.getReviewsFromReviewsDTO(reviewDTO);
			Reviews savedReview = reviewService.updateReview(review);
			return dtoConverter.getReviewsDTOFromReviews(savedReview);
		}
		return null;
	}
	
	@GetMapping("/viewreview/book")
	public List<ReviewsResponseDTO> viewReviewByBook(@RequestParam String bookname) {
		if(bookname!= null) {
			List<Reviews> listReviewByBook = reviewService.listAllReviewsByBook(bookname);
			List<ReviewsResponseDTO> reviewsDtoList = new ArrayList<>();
			ReviewsDTOMapper dtoConverter = new ReviewsDTOMapper();
			for(Reviews review: listReviewByBook) {
				ReviewsResponseDTO converterReview = dtoConverter.getReviewsDTOFromReviews(review);
				reviewsDtoList.add(converterReview);
			}
			return reviewsDtoList;
		}
		return null;
	}
	
	@GetMapping("/viewreview/author")
	public List<ReviewsResponseDTO> viewReviewByAuthor(@RequestParam String authorname) {
		if(authorname!= null) {
			List<Reviews> listReviewByAuthor = reviewService.listAllReviewsByAuthor(authorname);
			List<ReviewsResponseDTO> reviewsDtoList = new ArrayList<>();
			ReviewsDTOMapper dtoConverter = new ReviewsDTOMapper();
			for(Reviews review: listReviewByAuthor) {
				ReviewsResponseDTO converterReview = dtoConverter.getReviewsDTOFromReviews(review);
				reviewsDtoList.add(converterReview);
			}
			return reviewsDtoList;
		}
		return null;
	}
	
	@PostMapping("/placeorder")
	public AppOrderResponseDTO placeOrder(@Valid @RequestBody AppOrderRequestDTO requestDTO) {
		if(requestDTO != null) {
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			AppOrder order = dtoMapper.getAppOrderFromAppOrderRequestDTO(requestDTO);
			AppOrder placedOrder = orderService.addOrder(order);
			return dtoMapper.getAppOrderResponseDTOFromAppOrder(placedOrder);
		}
		return null;
	}
	
	@PutMapping("/cancelorder")
	public AppOrderResponseDTO cancelOrder(@RequestParam int oid) {
		AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
		AppOrder order = orderService.cancelOrder(oid);
		return dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
	}
	
	@GetMapping("/allplacedorders")
	public List<AppOrderResponseDTO> getAllPlacedOrders(@RequestParam String username){
		if(username != null) {
			List<AppOrder> orderList = orderService.viewOrdersByCustomer(username);
			List<AppOrderResponseDTO> orderResponseList = new ArrayList<>();
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			for(AppOrder order: orderList) {
				orderResponseList.add(dtoMapper.getAppOrderResponseDTOFromAppOrder(order));
			}
			return orderResponseList;
		}
		return null;
	}
	
	@GetMapping("/vieworderbyid")
	public AppOrderResponseDTO viewOrderById(@RequestParam int oid) {
		AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
		AppOrder order = orderService.viewOrderById(oid);
		return dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
	}
}
