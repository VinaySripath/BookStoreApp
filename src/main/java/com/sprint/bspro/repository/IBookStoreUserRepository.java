package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.BookStoreUser;
@Repository
public interface IBookStoreUserRepository extends JpaRepository<BookStoreUser, Integer> {

}
