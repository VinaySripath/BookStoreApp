package com.sprint.bspro.service;

import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Author;
@Service
public interface IAuthorService {
	public Author createAppAuthor(Author a);
	public Author updateAuthor(Author a);
	public Author updateAuthorByName(Author author, String username);
	public Author updateAuthorStatus(int usercode, String status);
	public Author viewAuthor(int usercode);
	public Author viewAuthorByName(String username);
	
}
