package com.sprint.bspro.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.ContactInfo;
import com.sprint.bspro.repository.IAppCustomerRepository;
@Service
public class AppCustomerServiceImpl implements IAppCustomerService {

	@Autowired
	IAppCustomerRepository customerRepository;
	@Override
	public AppCustomer createAppCustomer(AppCustomer customer) {
		if(customer != null) {
			customerRepository.save(customer);
			customer.setUserCode(customerRepository.getAppCustomerByUsername(customer.getUsername()).getUserCode());
			return customer;
		}
		return null;
	}

	@Override
	public List<AppCustomer> listCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public AppCustomer deleteCustomer(int customerId){
		if(customerId != 0) {
			return customerRepository.findById(customerId).get();
		}
		return null;
	}

	@Override
	@Transactional 
	public AppCustomer updateCustomer(AppCustomer customer) {
		if(customer != null) {
			int id = customer.getUserCode();
			System.out.println("customer id : "+id);
			AppCustomer savedCustomer = customerRepository.findById(id).get();
			if(savedCustomer != null) {
				if(customer.getContactInfo() != null) {
					ContactInfo cinfo = savedCustomer.getContactInfo();
					System.out.println("customer country : "+cinfo.getCountry());
					
					ContactInfo newInfo = customer.getContactInfo();
					System.out.println("new Country : "+newInfo.getCountry());
					if(newInfo.getCity()!= null) {
						cinfo.setCity(newInfo.getCity());
					}
					if(newInfo.getCountry()!= null) {
						cinfo.setCountry(newInfo.getCountry());
					}
					if(newInfo.getEmail()!= null) {
						cinfo.setEmail(newInfo.getEmail());
					}
					if(newInfo.getHouseAddress()!= null) {
						cinfo.setHouseAddress(newInfo.getHouseAddress());
					}
					if(newInfo.getPhone()!= 0) {
						cinfo.setPhone(newInfo.getPhone());
					}
					savedCustomer.setContactInfo(cinfo);
				}
				if(customer.getFullName()!= null) {
					savedCustomer.setFullName(customer.getFullName());
				}
				return savedCustomer;
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public AppCustomer updateCustomerByUsername(AppCustomer customer, String username) {
		if(customer != null) {
			AppCustomer savedCustomer = customerRepository.getAppCustomerByUsername(username);
			if(savedCustomer != null) {
				if(customer.getContactInfo() != null) {
					savedCustomer.setContactInfo(customer.getContactInfo());
				}
				if(customer.getFullName()!= null) {
					savedCustomer.setFullName(customer.getFullName());
				}
				return savedCustomer;
			}
		}
		return null;
	}

	@Override
	public AppCustomer viewCustomer(int userCode) {
		if(userCode != 0) {
			return customerRepository.findById(userCode).get();
		}
		return null;
	}

	@Override
	public AppCustomer viewCustomerByUserName(String username) {
		if(username != null) {
			return customerRepository.getAppCustomerByUsername(username);
		}
		return null;
	}
	
}
