package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.Author;
@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {

}
