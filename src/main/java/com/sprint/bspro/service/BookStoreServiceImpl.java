package com.sprint.bspro.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.Admin;
import com.sprint.bspro.entity.AppCustomer;
import com.sprint.bspro.entity.Author;
import com.sprint.bspro.entity.BookStoreUser;
import com.sprint.bspro.repository.IBookStoreUserRepository;
@Service
public class BookStoreServiceImpl implements IBookStoreUserService{
	@Autowired
	IBookStoreUserRepository bookStoreUserRepository;
	@Override
	public String appLogin(String username, String password) {
		System.out.println("in login check service");
		BookStoreUser bsuser = bookStoreUserRepository.getBookStoreUserByUsername(username);
		System.out.println("in login check service--------------"+ bsuser);
		if(bsuser != null) {
			System.out.println("in login check service inside if user");
			String storedPassword = bsuser.getPassword();
			if(password.equals(storedPassword)) {
				System.out.println("in login check service inside if password"+bsuser.getUserrole());
				return bsuser.getUserrole();
			}
		}
		return null;
	}
	@Override
	public String appCheckUser(String username) {
		System.out.println("in login check service");
		BookStoreUser bsuser = bookStoreUserRepository.getBookStoreUserByUsername(username);
		System.out.println("in login check service--------------"+ bsuser);
		if(bsuser != null) {
			String role = bsuser.getUserrole();
			if(role.equals("admin")) {
				Admin admin = (Admin)bsuser;
				return admin.getContactInfo().getEmail();
			}
			if(role.equals("customer")) {
				AppCustomer customer = (AppCustomer)bsuser;
				return customer.getContactInfo().getEmail();
			}
			if(role.equals("author")) {
				Author author = (Author)bsuser;
				return author.getContactInfo().getEmail();
			}
		}
		return null;
	}
	@Override
	@Transactional
	public Boolean appUpdatePassword(String password, String username) {
		BookStoreUser user = bookStoreUserRepository.getBookStoreUserByUsername(username);
//		System.out.println(user.getPassword()+"------------------");
		if(user != null) {
			user.setPassword(password);
			System.out.println(user.getPassword()+"------------------");
			return true;
		}
		return false;
	}

}
