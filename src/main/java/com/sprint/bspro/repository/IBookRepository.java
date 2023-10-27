package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.Book;

public interface IBookRepository extends JpaRepository<Book, Integer> {

}
