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

import com.sprint.bspro.dto.AdminRequestDTO;
import com.sprint.bspro.dto.AdminResponseDTO;
import com.sprint.bspro.dto.AppOrderResponseDTO;
import com.sprint.bspro.dto.AuthorResponseDTO;
import com.sprint.bspro.dto.BookRequestDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.dto.ReviewsResponseDTO;
import com.sprint.bspro.entity.Admin;
import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.exceptions.InvalidUserNameException;
import com.sprint.bspro.service.IAdminService;
import com.sprint.bspro.service.IAppOrderService;
import com.sprint.bspro.service.IAuthorService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.service.IReviewsService;
import com.sprint.bspro.util.AdminDTOMapper;
import com.sprint.bspro.util.AppOrderDTOMapper;
import com.sprint.bspro.util.AuthorDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;
import com.sprint.bspro.util.ReviewsDTOMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "All Admin Specific Admin Endpoints")
@CrossOrigin
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
	@Autowired
	IBookService bookService;
	@Autowired
	IAdminService adminService;
	@Autowired
	IAuthorService authorService;
	@Autowired
	IAppOrderService orderService;
	@Autowired
	IReviewsService reviewService;
	
	@ApiOperation(value="End point to get Book by Id, takes one param - id ")
	@GetMapping("/bookinfo")
	public ResponseEntity<BookResponseDTO> getBookById(@RequestParam int id) {
		 Book book = bookService.getBookById(id);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	
	@ApiOperation(value="End point to get Book by book name, takes one param - title")
	@GetMapping("/title/data")
	public ResponseEntity<BookResponseDTO> getBookByTitle(@RequestParam String title) {
		 Book book = bookService.getBookByTitle(title);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	
	@PutMapping("/updateinventory")
	public BookResponseDTO updateBookQuantity(@Valid @RequestBody BookRequestDTO requestDTO){
		if(requestDTO.getTitle()!= null && requestDTO.getAvailableQuantity()!=0) {
		Book book = bookService.updateAvailableQuantity(requestDTO.getTitle(), requestDTO.getAvailableQuantity());
		BookDTOMapper dtoMapper = new BookDTOMapper();
		return dtoMapper.getBookDTOFromBook(book);
		}
		return null;
	}
	
	
	
	@GetMapping("/viewadmin")
	public AdminResponseDTO getAdminByUserCode(@RequestParam int usercode) {
		AdminDTOMapper dtoConverter = new AdminDTOMapper();
		return dtoConverter.getAdminDTOFromAdmin(adminService.viewAdmin(usercode));
	}
	
	@GetMapping("/viewadminbyname")
	public AdminResponseDTO getAdminByUserName(@RequestParam String username) throws InvalidUserNameException{
		AdminDTOMapper dtoConverter = new AdminDTOMapper();
		return dtoConverter.getAdminDTOFromAdmin(adminService.viewAdminByUserName(username));
	}
	
	@PutMapping("/updateadmin")
	public AdminResponseDTO updateAdmin(@Valid @RequestBody AdminRequestDTO adminDTO) {
		if(adminDTO != null) {
			AdminDTOMapper dtoConverter = new AdminDTOMapper();
			Admin admin = dtoConverter.getAdminFromAdminDTO(adminDTO);
			Admin savedAdmin = adminService.updateAdmin(admin);
			return dtoConverter.getAdminDTOFromAdmin(savedAdmin);
		}
		return null;
	}
	
	@PutMapping("/updateadminbyname")
	public AdminResponseDTO updateAdminByName(@Valid @RequestBody AdminRequestDTO adminDTO, @RequestParam String username) throws InvalidUserNameException {
		if(adminDTO != null) {
			AdminDTOMapper dtoConverter = new AdminDTOMapper();
			Admin admin = dtoConverter.getAdminFromAdminDTO(adminDTO);
			Admin savedAdmin = adminService.updateAdminByName(admin, username);
			return dtoConverter.getAdminDTOFromAdmin(savedAdmin);
		}
		return null;
	}
	
	@PutMapping("/updateauthorstatus")
	public AuthorResponseDTO updateAuthorStatus(@RequestParam int usercode, @RequestParam String status) {
		if(status != null && usercode != 0) {
			Author author = authorService.updateAuthorStatus(usercode, status);
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			return dtoConverter.getAuthorDTOFromAuthor(author);
		}
		return null;
	}
	
	@GetMapping("/viewauthor")
	public AuthorResponseDTO getAuthorByUserCode(@RequestParam int usercode) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthor(usercode));
	}
	
	@GetMapping("/viewauthorbyname")
	public AuthorResponseDTO getAuthorByUserName(@RequestParam String username) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthorByName(username));
	}
	
	@PutMapping("/orderstatus")
	public AppOrderResponseDTO updateOrderStatus(@RequestParam String status, @RequestParam int oid ) {
		if(status != null && oid != 0) {
			AppOrder updatedOrder = orderService.updateOrderStatus(status, oid);
			if(updatedOrder != null) {
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			AppOrderResponseDTO orderDTO = dtoMapper.getAppOrderResponseDTOFromAppOrder(updatedOrder);
			return orderDTO;
			}
			return null;
		}
		return null;
	}
	
	@GetMapping("/orderlist")
	public List<AppOrderResponseDTO> viewAllOrders (){
		AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
		List<AppOrder> allOrders = orderService.viewAllOrders();
		List<AppOrderResponseDTO> allOrdersDto = new ArrayList<>();
		for(AppOrder order : allOrders) {
			AppOrderResponseDTO orderDto = dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
			allOrdersDto.add(orderDto);
		}
		return allOrdersDto;
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
	
	@GetMapping("/allbooks/quantity")
	public List<BookResponseDTO> getAllBooksByQuantity(@RequestParam int maxquant){
		List<Book> books = bookService.getBookByQuantity(maxquant);
		List<BookResponseDTO> booksDtos = new ArrayList<>();
		BookDTOMapper brc = new BookDTOMapper();
		for(Book book: books) {
			BookResponseDTO bookDto = brc.getBookDTOFromBook(book);
			booksDtos.add(bookDto);
		}
		return booksDtos;
	}
	
	@GetMapping("/getorder/book")
	public List<AppOrderResponseDTO> getAllOrdersByBook(@RequestParam String bookname) {
		if(bookname != null) {
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			List<AppOrder> bookOrders = bookService.getAllOrdersByBook(bookname);
			List<AppOrderResponseDTO> bookOrdersDto = new ArrayList<>();
			for(AppOrder order: bookOrders) {
				AppOrderResponseDTO appOrderDto = dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
				bookOrdersDto.add(appOrderDto);
			}
			return bookOrdersDto;
		}
		return null;
	}
	
	@GetMapping("/getorder/author")
	public List<AppOrderResponseDTO> getAllOrdersByAuthor(@RequestParam String authorname) {
		if(authorname != null) {
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			List<AppOrder> bookOrders = authorService.getAllOrdersByAuthor(authorname);
			List<AppOrderResponseDTO> bookOrdersDto = new ArrayList<>();
			for(AppOrder order: bookOrders) {
				AppOrderResponseDTO appOrderDto = dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
				bookOrdersDto.add(appOrderDto);
			}
			return bookOrdersDto;
		}
		return null;
	}
	
	@GetMapping("/getallorder")
	public List<AppOrderResponseDTO> getAllOrders() {
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			List<AppOrder> bookOrders = orderService.viewAllOrders();
			List<AppOrderResponseDTO> bookOrdersDto = new ArrayList<>();
			for(AppOrder order: bookOrders) {
				AppOrderResponseDTO appOrderDto = dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
				bookOrdersDto.add(appOrderDto);
			}
			return bookOrdersDto;
	}
	
	@GetMapping("/getorder/bookcategory")
	public List<AppOrderResponseDTO> getAllOrdersByBookCategory(@RequestParam String category) {
		if(category != null) {
			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
			List<AppOrder> bookOrders = bookService.getAllOrdersByBookCategory(category);
			List<AppOrderResponseDTO> bookOrdersDto = new ArrayList<>();
			for(AppOrder order: bookOrders) {
				AppOrderResponseDTO appOrderDto = dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
				bookOrdersDto.add(appOrderDto);
			}
			return bookOrdersDto;
		}
		return null;
	}
}
