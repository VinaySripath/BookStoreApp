package com.sprint.bspro.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.bspro.entity.AppOrder;

public interface IAppOrderRepository extends JpaRepository<AppOrder, Integer> {

}
