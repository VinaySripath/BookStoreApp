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

import com.sprint.bspro.dto.AppCustomerRequestDTO;
import com.sprint.bspro.dto.AppCustomerResponseDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.service.IAppCustomerService;
import com.sprint.bspro.service.IBookService;
import com.sprint.bspro.util.AppCustomerDTOMapper;
import com.sprint.bspro.util.BookDTOMapper;

@RestController
@RequestMapping("/user")
public class CustomerController {
	@Autowired
	IBookService bookService;
	@Autowired
	IAppCustomerService appCustomerService;
	
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
	public AppCustomerResponseDTO addCustomer(@RequestBody AppCustomerRequestDTO customerDTO) {
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
	public AppCustomerResponseDTO updateCustomer(@RequestBody AppCustomerRequestDTO customerDTO) {
		if(customerDTO != null) {
			AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
			AppCustomer customer = dtoConverter.getAppCustomerFromAppCustomerDTO(customerDTO);
			AppCustomer savedCustomer = appCustomerService.updateCustomer(customer);
			return dtoConverter.getAppCustomerDTOFromAppCustomer(savedCustomer);
		}
		return null;
	}
	
	@PutMapping("/updatecustomerbyname")
	public AppCustomerResponseDTO updateCustomerByUsername(@RequestBody AppCustomerRequestDTO customerDTO, @RequestParam String username) {
		if(customerDTO != null) {
			
			AppCustomerDTOMapper dtoConverter = new AppCustomerDTOMapper();
			AppCustomer customer = dtoConverter.getAppCustomerFromAppCustomerDTO(customerDTO);
			AppCustomer savedCustomer = appCustomerService.updateCustomerByUsername(customer, username);
			return dtoConverter.getAppCustomerDTOFromAppCustomer(savedCustomer);
		}
		return null;
	}
}
