package com.sprint.bspro.service;

import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.AppOrder;
@Service
public interface IAppOrderService {
	public AppOrder cancelOrder(AppOrder od);
	public AppOrder addOrder(AppOrder od);
	public AppOrder updateOrder(AppOrder od);
}
