package com.sprint.bspro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/addcustomer")
	public AppCustomerResponseDTO addCustomer(@Valid @RequestBody AppCustomerRequestDTO customerDTO) {
		if(customerDTO != null) {
			AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
			
			AppCustomer customer = dtoConverter.getAppCustomerFromAppCustomerDTO(customerDTO);
			AppCustomer savedCustomer = appCustomerService.createAppCustomer(customer);
			return dtoConverter.getAppCustomerDTOFromAppCustomer(savedCustomer);
		}
		return null;
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
}
