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

import com.sprint.bspro.dto.AdminRequestDTO;
import com.sprint.bspro.dto.AdminResponseDTO;
import com.sprint.bspro.dto.AppOrderResponseDTO;
import com.sprint.bspro.dto.AuthorResponseDTO;
import com.sprint.bspro.dto.BookRequestDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.entity.Admin;
import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.exceptions.InvalidUserNameException;
import com.sprint.bspro.service.IAdminService;
import com.sprint.bspro.service.IAppOrderService;
import com.sprint.bspro.service.IAuthorService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.util.AdminDTOMapper;
import com.sprint.bspro.util.AppOrderDTOMapper;
import com.sprint.bspro.util.AuthorDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "All Admin Specific Admin Endpoints")
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IBookService bookService;
	@Autowired
	IAdminService adminService;
	@Autowired
	IAuthorService authorService;
	@Autowired
	IAppOrderService orderService;
	
	@ApiOperation(value="End point to get Book by Id, takes one param - id ")
	@GetMapping("/bookinfo")
	
	/** * This method retrieves a book from the Book Store App's database based on the provided book ID. 
	**@param id The ID of the book to retrieve.
	** @return A ResponseEntity containing a BookResponseDTO object representing the retrieved book, along with an HTTP status code. */
	
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
	
	/** * This method updates the available quantity of a book in the Book Store App's database based on the provided BookRequestDTO object.
       * * @param requestDTO A valid BookRequestDTO object containing the updated book information.
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
	
	/** * This method adds a new admin to the Book Store App's database based on the provided AdminRequestDTO object.
	* @param adminDTO A valid AdminRequestDTO object containing the details of the admin to be added.
	* * @return An AdminResponseDTO object representing the added admin, or null if the adminDTO is null.
	*/
	@PostMapping("/createadmin")
	public AdminResponseDTO addAdmin(@Valid @RequestBody AdminRequestDTO adminDTO) {
		if(adminDTO != null) {
			AdminDTOMapper dtoConverter = new AdminDTOMapper();
			
			Admin admin = dtoConverter.getAdminFromAdminDTO(adminDTO);
			Admin savedAdmin = adminService.createAppAdmin(admin);
			return dtoConverter.getAdminDTOFromAdmin(savedAdmin);
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
}
