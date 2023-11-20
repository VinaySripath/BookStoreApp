package com.sprint.bspro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.Author;
@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {

	public Author getAuthorByUsername(String username);
	public List<Author> findByStatus(String status);
}
