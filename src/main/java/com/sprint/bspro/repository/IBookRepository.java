package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.Book;
@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

}
