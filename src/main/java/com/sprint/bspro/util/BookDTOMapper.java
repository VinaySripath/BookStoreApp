package com.sprint.bspro.util;

import com.sprint.bspro.dto.BookRequestDTO;
import com.sprint.bspro.dto.BookResponseDTO;
import com.sprint.bspro.entity.Book;

public class BookDTOMapper {
	
	public Book getBookFromBookDTO(BookRequestDTO dto) {
		Book bk = new Book();
		if(dto.getId()!= 0) {
			bk.setBookId(dto.getId());
		}
		bk.setTitle(dto.getTitle());
		bk.setPrice(dto.getPrice());
		bk.setCategory(dto.getCategory());
		bk.setPages(dto.getPages());
		bk.setDescription(dto.getDescription());
		System.out.println("the book is ! "+ bk.getBookId());
		return bk;
	}
	
	public BookResponseDTO getBookDTOFromBook(Book book) {
		BookResponseDTO bdto = new BookResponseDTO();
		bdto.setTitle(book.getTitle());
		bdto.setDescription(book.getDescription());
		bdto.setPrice(book.getPrice());
		bdto.setId(book.getBookId());
		bdto.setCategory(book.getCategory());
		bdto.setPages(book.getPages());
		return bdto;
	}
}
