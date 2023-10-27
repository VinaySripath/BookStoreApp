package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.AppCustomer;
@Repository
public interface IAppCustomerRepository extends JpaRepository<AppCustomer, Integer> {

}
