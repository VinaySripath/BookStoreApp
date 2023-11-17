package com.sprint.bspro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.bspro.dto.AuthorRequestDTO;
import com.sprint.bspro.dto.AuthorResponseDTO;
import com.sprint.bspro.dto.BookRequestDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.service.IAuthorService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.util.AuthorDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;
@CrossOrigin
@RestController
@RequestMapping("/author")
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
	
	
	@PostMapping("/add")
	public BookResponseDTO createBook(@Valid @RequestBody BookRequestDTO bdto, @RequestParam String aname) {
		BookDTOMapper bconvert = new BookDTOMapper();
		Book book = bconvert.getBookFromBookDTO(bdto);
		System.out.println(book.getTitle()+"   "+ book.getDescription());
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
		System.out.println(book);
		Book b = bookService.editBook(book);
		return bconvert.getBookDTOFromBook(b);
	}
	/** * Adds a new author based on the provided AuthorRequestDTO.
	 * 
	 * @param authorDTO The AuthorRequestDTO containing the details of the author to be added.
	 * @return The AuthorResponseDTO representing the added author.
	 */
	
	@PostMapping("/createauthor")
	public AuthorResponseDTO addAuthor(@Valid @RequestBody AuthorRequestDTO authorDTO) {
		if(authorDTO != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			
			Author author = dtoConverter.getAuthorFromAuthorDTO(authorDTO);
			Author savedAuthor = authorService.createAppAuthor(author);
			return dtoConverter.getAuthorDTOFromAuthor(savedAuthor);
		}
		return null;
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
	public AuthorResponseDTO getAuthorByUserName(@RequestParam String username) {
		AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
		return dtoConverter.getAuthorDTOFromAuthor(authorService.viewAuthorByName(username));
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
	
}
