package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.Author;

public interface IAuthorRepository extends JpaRepository<Author, Integer> {

}
