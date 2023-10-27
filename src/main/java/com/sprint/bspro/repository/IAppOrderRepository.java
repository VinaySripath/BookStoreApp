package com.sprint.bspro.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.AppOrder;
@Repository
public interface IAppOrderRepository extends JpaRepository<AppOrder, Integer> {

}
