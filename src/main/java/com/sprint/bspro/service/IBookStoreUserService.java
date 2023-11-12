package com.sprint.bspro.service;

import org.springframework.stereotype.Service;

@Service
public interface IBookStoreUserService {
	public String appLogin(String username, String password);
	public String appCheckUser(String username);
	public Boolean appUpdatePassword(String password, String username);
}
