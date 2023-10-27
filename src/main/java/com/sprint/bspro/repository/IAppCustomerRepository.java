package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.AppCustomer;

public interface IAppCustomerRepository extends JpaRepository<AppCustomer, Integer> {

}
