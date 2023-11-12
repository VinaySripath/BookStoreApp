package com.sprint.bspro.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.AppOrder;
@Repository
public interface IAppOrderRepository extends JpaRepository<AppOrder, Integer> {
	public List<AppOrder> getAppOrderByCustomer(AppCustomer customer);
}
