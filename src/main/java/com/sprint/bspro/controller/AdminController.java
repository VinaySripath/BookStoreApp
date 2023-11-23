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
	
	/** * This method retrieves a book from the Book Store App's database based on the provided book ID. 
	*@param id The ID of the book to retrieve.
	* @return A ResponseEntity containing a BookResponseDTO object representing the retrieved book, along with an HTTP status code. 
	*/
	
	public ResponseEntity<BookResponseDTO> getBookById(@RequestParam int id) {
		 Book book = bookService.getBookById(id);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	
	@ApiOperation(value="End point to get Book by book name, takes one param - title")
	@GetMapping("/title/data")
	/** This method retrieves a book from the Book Store App's database based on the provided book title.
	* @param title The title of the book to retrieve. 
	* @return A ResponseEntity containing a BookResponseDTO object representing the retrieved book, along with an HTTP status code. 
	*/
	
	public ResponseEntity<BookResponseDTO> getBookByTitle(@RequestParam String title) {
		 Book book = bookService.getBookByTitle(title);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	
	/** This method updates the available quantity of a book in the Book Store App's database based on the provided BookRequestDTO object.
       * @param requestDTO A valid BookRequestDTO object containing the updated book information.
       * @return A BookResponseDTO object representing the updated book, or null if the requestDTO is null or the available quantity is 0.	 
	 */
	
	@PutMapping("/updateinventory")
	public BookResponseDTO updateBookQuantity(@Valid @RequestBody BookRequestDTO requestDTO){
		if(requestDTO.getTitle()!= null && requestDTO.getAvailableQuantity()!=0) {
		Book book = bookService.updateAvailableQuantity(requestDTO.getTitle(), requestDTO.getAvailableQuantity());
		BookDTOMapper dtoMapper = new BookDTOMapper();
		return dtoMapper.getBookDTOFromBook(book);
		}
		return null;
	}
	
	/** * This method retrieves an admin from the Book Store App's database based on the provided user code.
	 * 
	 * @param usercode The user code of the admin to retrieve. 
	 * @return An AdminResponseDTO object representing the retrieved admin, or null if the admin is not found.
	 */
	
	@GetMapping("/viewadmin")
	public AdminResponseDTO getAdminByUserCode(@RequestParam int usercode) {
		AdminDTOMapper dtoConverter = new AdminDTOMapper();
		return dtoConverter.getAdminDTOFromAdmin(adminService.viewAdmin(usercode));
	}
	/** * This method retrieves an admin from the Book Store App's database based on the provided username.
	 * 
	 * @param username The username of the admin to retrieve.
	 * @return  An AdminResponseDTO object representing the retrieved admin.
	 * @throws InvalidUserNameException If the provided username is invalid or does not exist in the database.
	 */
	
	@GetMapping("/viewadminbyname")
	public AdminResponseDTO getAdminByUserName(@RequestParam String username) throws InvalidUserNameException{
		AdminDTOMapper dtoConverter = new AdminDTOMapper();
		return dtoConverter.getAdminDTOFromAdmin(adminService.viewAdminByUserName(username));
	}
	/** *This method updates the details of an admin in the Book Store App's database based on the provided AdminRequestDTO object.
	 * 	
	 * @param adminDTO A valid AdminRequestDTO object containing the updated admin information.
	 * @return An AdminResponseDTO object representing the updated admin, or null if the adminDTO is null.
	 */

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
	/** * This method updates the details of an admin in the Book Store App's database based on the provided AdminRequestDTO object and username.
	 * 
	 * @param adminDTO A valid AdminRequestDTO object containing the updated admin information.
	 * @param username The username of the admin to update.
	 * @return An AdminResponseDTO object representing the updated admin, or null if the adminDTO is null.
	 * @throws InvalidUserNameException If the provided username is invalid or does not exist in the database.
	 */
	
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
	/** * This method updates the status of an author in the Book Store App's database based on the provided user code and status.
	 * 
	 * @param usercode The user code of the author to update.
	 * @param status The new status to assign to the author.
	 * @return An AuthorResponseDTO object representing the updated author, or null if either the status or usercode is invalid.
	 */
	
	
	@PutMapping("/updateauthorstatus")
	public AuthorResponseDTO updateAuthorStatus(@RequestParam int usercode, @RequestParam String status) {
		if(status != null && usercode != 0) {
			Author author = authorService.updateAuthorStatus(usercode, status);
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			return dtoConverter.getAuthorDTOFromAuthor(author);
		}
		return null;
	}
	/** * This method retrieves an author from the Book Store App's database based on the provided user code.
	 * 
	 * @param usercode The user code of the author to retrieve.
	 * @return An AuthorResponseDTO object representing the retrieved author, or null if the author is not found.
	 */

	@GetMapping("/viewauthor")
	public AuthorResponseDTO getAuthorByUserCode(@RequestParam int usercode) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthor(usercode));
	}
	/** * This method retrieves an author from the Book Store App's database based on the provided username.
	 * 
	 * @param username The username of the author to retrieve.
	 * @return An AuthorResponseDTO object representing the retrieved author, or null if the author is not found.
	 */
	
	
	@GetMapping("/viewauthor/status")
	public List<AuthorResponseDTO> getAuthorByStatus(@RequestParam String status) {
		if(status != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			List<AuthorResponseDTO> authorDtoList = new ArrayList<>();
			List<Author>authorList = authorService.viewAllAuthorByStatus(status);
			for(Author a : authorList){
			authorDtoList.add(dtoConverter.getAuthorDTOFromAuthor(a));
			}
			return authorDtoList;
		}
		return null;
	}
	
	@GetMapping("/viewallauthor")
	public List<AuthorResponseDTO> getAllAuthor() {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		List<AuthorResponseDTO> authorDtoList = new ArrayList<>();
		List<Author>authorList = authorService.viewAllAuthor();
		for(Author a : authorList){
		authorDtoList.add(dtoConverter.getAuthorDTOFromAuthor(a));
		}
		return authorDtoList;
	}
	
	@GetMapping("/viewauthorbyname")
	public AuthorResponseDTO getAuthorByUserName(@RequestParam String username) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthorByName(username));
	}
	
	/** * This method updates the status of an order in the Book Store App's database based on the provided status and order ID.
	 * 
	 * @param status The new status to assign to the order.
	 * @param oid The order ID of the order to update.
	 * @return An AppOrderResponseDTO object representing the updated order, or null if either the status or oid is invalid. 
	 */
	
	@PutMapping("/orderstatus")
	public AppOrderResponseDTO updateOrderStatus(@RequestBody String status, @RequestParam int oid ) {
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
	/** *@viewAllOrders method retrieves all orders from the Book Store App's database and returns them as a list of AppOrderResponseDTO objects.
	 * 
	 * @return A list of AppOrderResponseDTO objects representing all orders in the database.
	 */
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
	/** * Returns a list of reviews for a given book.
	 * 
	 * @param bookname the name of the book to retrieve reviews for
	 * @return a list of reviews for the given book
	 */
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
	/** * Returns a list of reviews for a given author.
	 * 
	 * @param authorname the name of the author to retrieve reviews for
	 * @return a list of reviews for the given author
	 */
	
	@GetMapping("/viewreview/customer")
	public List<ReviewsResponseDTO> viewReviewByCustomer(@RequestParam String cname) {
		if(cname!= null) {
			List<Reviews> listReviewByCustomer = reviewService.listAllReviewsByCustomer(cname);
			List<ReviewsResponseDTO> reviewsDtoList = new ArrayList<>();
			ReviewsDTOMapper dtoConverter = new ReviewsDTOMapper();
			for(Reviews review: listReviewByCustomer) {
				ReviewsResponseDTO converterReview = dtoConverter.getReviewsDTOFromReviews(review);
				reviewsDtoList.add(converterReview);
			}
			return reviewsDtoList;
		}
		return null;
	}
	
	@GetMapping("/viewreview/category")
	public List<ReviewsResponseDTO> viewReviewByCategory(@RequestParam String caname) {
		if(caname!= null) {
			List<Reviews> listReviewByCategory = reviewService.listAllReviewsByCategory(caname);
			List<ReviewsResponseDTO> reviewsDtoList = new ArrayList<>();
			ReviewsDTOMapper dtoConverter = new ReviewsDTOMapper();
			for(Reviews review: listReviewByCategory) {
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
	
	/** * Returns a list of books for a given category.
	 * 
	 * @param category the category of the books to retrieve
	 * @return a list of books for the given category
	 */
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
	/** Returns a list of books that match the given search key.
	 * 
	 * @param key the search key to use
	 * @return a list of books that match the given search key
	 */
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
	/** Returns a list of books for a given author.
	 * 
	 * @param authorname the name of the author to retrieve books for
	 * @return a list of books for the given author
	 */
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
	
//	@GetMapping("/getallorder")
//	public List<AppOrderResponseDTO> getAllOrders() {
//			AppOrderDTOMapper dtoMapper = new AppOrderDTOMapper();
//			List<AppOrder> bookOrders = orderService.viewAllOrders();
//			List<AppOrderResponseDTO> bookOrdersDto = new ArrayList<>();
//			for(AppOrder order: bookOrders) {
//				AppOrderResponseDTO appOrderDto = dtoMapper.getAppOrderResponseDTOFromAppOrder(order);
//				bookOrdersDto.add(appOrderDto);
//			}
//			return bookOrdersDto;
//	}
	
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
