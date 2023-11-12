package com.sprint.bspro.controller;

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
	@PostMapping("/add")
	public BookResponseDTO createBook(@RequestBody BookRequestDTO bdto) {
		BookDTOMapper bconvert = new BookDTOMapper();
		Book book = bconvert.getBookFromBookDTO(bdto);
		System.out.println(book.getTitle()+"   "+ book.getDescription());
		Book b = bookService.createBook(book);
		return bconvert.getBookDTOFromBook(b);
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
	
	@PutMapping("/updatebookinfo")
	public BookResponseDTO updateBook(@RequestBody BookRequestDTO bdto) {
		BookDTOMapper bconvert = new BookDTOMapper();
		Book book = bconvert.getBookFromBookDTO(bdto);
		System.out.println(book);
		Book b = bookService.editBook(book);
		return bconvert.getBookDTOFromBook(b);
	}
	
	@PostMapping("/createauthor")
	public AuthorResponseDTO addAuthor(@RequestBody AuthorRequestDTO authorDTO) {
		if(authorDTO != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			
			Author author = dtoConverter.getAuthorFromAuthorDTO(authorDTO);
			Author savedAuthor = authorService.createAppAuthor(author);
			return dtoConverter.getAuthorDTOFromAuthor(savedAuthor);
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
	
	@PutMapping("/updateauthor")
	public AuthorResponseDTO updateAuthor(@RequestBody AuthorRequestDTO authorDTO) {
		if(authorDTO != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			Author author = dtoConverter.getAuthorFromAuthorDTO(authorDTO);
			Author savedAuthor = authorService.updateAuthor(author);
			return dtoConverter.getAuthorDTOFromAuthor(savedAuthor);
		}
		return null;
	}
	
	@PutMapping("/updateauthorbyname")
	public AuthorResponseDTO updateAuthorByName(@RequestBody AuthorRequestDTO authorDTO, @RequestParam String username) {
		if(authorDTO != null) {
			AuthorDTOMapper dtoConverter = new AuthorDTOMapper();
			Author author = dtoConverter.getAuthorFromAuthorDTO(authorDTO);
			Author savedAuthor = authorService.updateAuthorByName(author, username);
			return dtoConverter.getAuthorDTOFromAuthor(savedAuthor);
		}
		return null;
	}
	
}
