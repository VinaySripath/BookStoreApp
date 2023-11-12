package com.sprint.bspro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.AppOrder;
import com.sprint.bspro.entity.Book;
import com.sprint.bspro.repository.IAppCustomerRepository;
import com.sprint.bspro.repository.IAppOrderRepository;
import com.sprint.bspro.repository.IBookRepository;
@Service
public class AppOrderServiceImpl implements IAppOrderService {
	@Autowired
	IAppOrderRepository orderRepository;
	@Autowired
	IAppCustomerRepository customerRepository;
	@Autowired
	IBookRepository bookRepository;
	@Override
	@Transactional
	public AppOrder cancelOrder(int oid) {
		if(oid != 0) {
			AppOrder order = orderRepository.findById(oid).get();
			if(order.getOrderStatus().equals("placed successfully")) {
				order.setOrderStatus("cancelled");
			}
			return order;
		}
		return null;
	}

	@Override
	@Transactional
	public AppOrder addOrder(AppOrder order) {
		System.out.println("inside service--------------------------"+order.getCustomer().getUsername());
		Map<Book,Integer> orderDetail = new HashMap<>();
		for(Map.Entry<Book, Integer> e : order.getOrderDetails().entrySet()) {
			String name = e.getKey().getTitle();
			Book book = bookRepository.getBookByTitle(name);
			orderDetail.put(book, e.getValue());
		}
		String user = order.getCustomer().getUsername();
		AppCustomer customer = customerRepository.getAppCustomerByUsername(user);
		order.setOrderDetails(orderDetail);
		order.setCustomer(customer);
		System.out.println("inside service--------------------------"+order.getCustomer().getUsername());
		boolean placeOrder = true;
		for(Map.Entry<Book, Integer> e: order.getOrderDetails().entrySet()) {
			if(e.getKey().getAvailableQuantity()<e.getValue()) {
				placeOrder = false;
				break;
			}
		}
		if(placeOrder) {
			System.out.println("inside if place order----------------------- ");
			for(Map.Entry<Book, Integer> e: order.getOrderDetails().entrySet()) {
				e.getKey().setAvailableQuantity(e.getKey().getAvailableQuantity()-e.getValue());  
			}
			List<AppOrder> ordersList = customer.getAllPlacedOrders();
			ordersList.add(order);
			AppOrder savedOrder = orderRepository.save(order);
			for(Map.Entry<Book, Integer> e: order.getOrderDetails().entrySet()) {
				List<AppOrder>bookOrders = e.getKey().getOrders();
				bookOrders.add(savedOrder);
				e.getKey().setOrders(bookOrders);
			}
			return savedOrder;
		}
		return null;
	}

	@Override
	@Transactional
	public AppOrder updateOrderStatus(String status, int oid) {
		if(oid != 0) {
			AppOrder order = orderRepository.findById(oid).get();
			order.setOrderStatus(status);
			return order;
		}
		return null;
	}

	@Override
	public List<AppOrder> viewAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public List<AppOrder> viewOrdersByCustomer(String username) {
		if(username != null) {
			AppCustomer customer = customerRepository.getAppCustomerByUsername(username);
			if(customer != null) {
				return orderRepository.getAppOrderByCustomer(customer);
			}
		}
		return null;
	}

}
