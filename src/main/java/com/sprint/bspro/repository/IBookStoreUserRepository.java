package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.BookStoreUser;

public interface IBookStoreUserRepository extends JpaRepository<BookStoreUser, Integer> {

}
