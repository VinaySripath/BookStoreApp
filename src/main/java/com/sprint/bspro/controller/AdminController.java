package com.sprint.bspro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.bspro.dto.AdminRequestDTO;
import com.sprint.bspro.dto.AdminResponseDTO;
import com.sprint.bspro.dto.AuthorResponseDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.entity.Admin;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.service.IAdminService;
import com.sprint.bspro.service.IAuthorService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.util.AdminDTOMapper;
import com.sprint.bspro.util.AuthorDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	IBookService bookService;
	@Autowired
	IAdminService adminService;
	@Autowired
	IAuthorService authorService;
	
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
	
	@PostMapping("/createadmin")
	public AdminResponseDTO addAdmin(@RequestBody AdminRequestDTO adminDTO) {
		if(adminDTO != null) {
			AdminDTOMapper dtoConverter = new AdminDTOMapper();
			
			Admin admin = dtoConverter.getAdminFromAdminDTO(adminDTO);
			Admin savedAdmin = adminService.createAppAdmin(admin);
			return dtoConverter.getAdminDTOFromAdmin(savedAdmin);
		}
		return null;
	}
	
	@GetMapping("/viewadmin")
	public AdminResponseDTO getAdminByUserCode(@RequestParam int usercode) {
		AdminDTOMapper dtoConverter = new AdminDTOMapper();
		return dtoConverter.getAdminDTOFromAdmin(adminService.viewAdmin(usercode));
	}
	
	@GetMapping("/viewadminbyname")
	public AdminResponseDTO getAdminByUserName(@RequestParam String username) {
		AdminDTOMapper dtoConverter = new AdminDTOMapper();
		return dtoConverter.getAdminDTOFromAdmin(adminService.viewAdminByUserName(username));
	}
	
	@PutMapping("/updateadmin")
	public AdminResponseDTO updateAdmin(@RequestBody AdminRequestDTO adminDTO) {
		if(adminDTO != null) {
			AdminDTOMapper dtoConverter = new AdminDTOMapper();
			Admin admin = dtoConverter.getAdminFromAdminDTO(adminDTO);
			Admin savedAdmin = adminService.updateAdmin(admin);
			return dtoConverter.getAdminDTOFromAdmin(savedAdmin);
		}
		return null;
	}
	
	@PutMapping("/updateadminbyname")
	public AdminResponseDTO updateAdminByName(@RequestBody AdminRequestDTO adminDTO, @RequestParam String username) {
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
}
