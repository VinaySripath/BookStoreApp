package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
