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

	/**Performs login authentication for the application.
	 * 
	 * @param username the username provided by the user
	 * @param password the password provided by the user
	 * @return the user role if the login is successful, or null if the login fails
	 */	
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
	/** This method is used to check the user details for login.
	 * 
	 * @param username The username of the user.
	 * @return The email address of the user if they are an admin, null otherwise. 
	 */

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
	
	/** This method is used to update the password for a user.
	 * 
	 * @param password The new password to be set.
	 * @param username The username of the user.
	 * @return True if the password is updated successfully, false otherwise
	 */
	
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
