package com.sprint.bspro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.Admin;
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
