package com.sprint.bspro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.bspro.dto.AppOrderResponseDTO;
import com.sprint.bspro.dto.AuthorRequestDTO;
import com.sprint.bspro.dto.AuthorResponseDTO;
import com.sprint.bspro.dto.BookRequestDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.dto.ReviewsResponseDTO;
import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.entity.Reviews;
import com.sprint.bspro.service.IAuthorService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.service.IReviewsService;
import com.sprint.bspro.util.AppOrderDTOMapper;
import com.sprint.bspro.util.AuthorDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;
import com.sprint.bspro.util.ReviewsDTOMapper;
@CrossOrigin
@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {
	@Autowired
	IBookService bookService;
	@Autowired
	IAuthorService authorService;

	
	
	/** * This method creates a new book in the Book Store App's database based on the provided BookRequestDTO object and author name.
	 * 
	 * @param bdto A valid BookRequestDTO object containing the details of the book to create. 
	 * @param aname The name of the author associated with the book.
	 * @return A BookResponseDTO object representing the newly created book.
	 */
	
	@Autowired
	IReviewsService reviewService;
	@PostMapping("/add")
	public BookResponseDTO createBook(@Valid @RequestBody BookRequestDTO bdto, @RequestParam String aname) {
		BookDTOMapper bconvert = new BookDTOMapper();
		Book book = bconvert.getBookFromBookDTO(bdto);
		Book b = bookService.createBook(book, aname);
		return bconvert.getBookDTOFromBook(b);
	}
	/** * This method retrieves a book from the Book Store App's database based on the provided book ID. 
	 * 
	 * @param id The ID of the book to retrieve.
	 * @return A ResponseEntity object containing a BookResponseDTO object representing the retrieved book
	 */
	@GetMapping("/bookinfo")
	public ResponseEntity<BookResponseDTO> getBookById(@RequestParam int id) {
		 Book book = bookService.getBookById(id);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	/** * This method retrieves a book from the Book Store App's database based on the provided title.
	 * 
	 * @param title The title of the book to retrieve.
	 * @return A ResponseEntity object containing a BookResponseDTO object representing the retrieved book.
	 */
	@GetMapping("/title/data")
	public ResponseEntity<BookResponseDTO> getBookByTitle(@RequestParam String title) {
		 Book book = bookService.getBookByTitle(title);
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 
		 return new ResponseEntity<BookResponseDTO>(bresponse, HttpStatus.OK);
	}
	/** * This method deletes a book from the Book Store App's database based on the provided book ID.
	 * 
	 * @param id The ID of the book to delete.
	 * @return A BookResponseDTO object representing the deleted book, or null if the book is not found. 
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
	
	@DeleteMapping("/removebook")
	public BookResponseDTO deleteBookById(@RequestParam int id) {
		 Book book = bookService.deleteBook(id);
		 if(book != null) {
		 BookDTOMapper brc = new BookDTOMapper();
		 BookResponseDTO bresponse= brc.getBookDTOFromBook(book);
		 return bresponse;
		 }
		 return null;
	}
	
	/** * Updates a book based on the provided BookRequestDTO.
	 * 
	 * @param bdto The BookRequestDTO containing the updated book information.
	 * @return 
	 */
	
	@PutMapping("/updatebookinfo")
	public BookResponseDTO updateBook(@Valid @RequestBody BookRequestDTO bdto) {
		BookDTOMapper bconvert = new BookDTOMapper();
		Book book = bconvert.getBookFromBookDTO(bdto);
		Book b = bookService.editBook(book);
		return bconvert.getBookDTOFromBook(b);
	}
	
	
	/** * Retrieves an author based on the provided user code. 
	 * 
	 * @param usercode The user code of the author to be retrieved.
	 * @return The AuthorResponseDTO representing the retrieved author. 
	 */

	@GetMapping("/viewauthor")
	public AuthorResponseDTO getAuthorByUserCode(@RequestParam int usercode) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthor(usercode));
	}
	/** * Retrieves an author based on the provided username.
	 * 
	 * @param username The username of the author to be retrieved.
	 * @return The AuthorResponseDTO representing the retrieved author. 
	 */
	@GetMapping("/viewauthorbyname")
	public ResponseEntity<AuthorResponseDTO> getAuthorByUserName(@RequestParam String username) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return new ResponseEntity<AuthorResponseDTO>(dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthorByName(username)),HttpStatus.OK);
	}
	/** * Updates an existing author based on the provided AuthorRequestDTO.
	 * 
	 * @param authorDTO The AuthorRequestDTO containing the updated author information. 
	 * @return The AuthorResponseDTO representing the updated author.
	 */
	@PutMapping("/updateauthor")
	public AuthorResponseDTO updateAuthor(@Valid @RequestBody AuthorRequestDTO authorDTO) {
		if(authorDTO != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			Author author = dtoConverter.getAuthorFromAuthorDTO(authorDTO);
			Author savedAuthor = authorService.updateAuthor(author);
			return dtoConverter.getAuthorDTOFromAuthor(savedAuthor);
		}
		return null;
	}
	/** * Updates an existing author based on the provided AuthorRequestDTO and username. *
	 * 
	 * @param authorDTO The AuthorRequestDTO containing the updated author information.
	 * @param username The username of the author to be updated.
	 * @return The AuthorResponseDTO representing the updated author. 
	 */
	@PutMapping("/updateauthorbyname")
	public AuthorResponseDTO updateAuthorByName(@Valid @RequestBody AuthorRequestDTO authorDTO, @RequestParam String username) {
		if(authorDTO != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			Author author = dtoConverter.getAuthorFromAuthorDTO(authorDTO);
			Author savedAuthor = authorService.updateAuthorByName(author, username);
			return dtoConverter.getAuthorDTOFromAuthor(savedAuthor);
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
}
