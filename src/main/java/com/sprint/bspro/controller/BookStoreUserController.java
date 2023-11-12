package com.sprint.bspro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.bspro.dto.LoginDTO;
import com.sprint.bspro.dto.ResetPasswordDTO;
import com.sprint.bspro.entity.MailStructure;
import com.sprint.bspro.service.EmailServiceImpl;
import com.sprint.bspro.service.IBookStoreUserService;
@CrossOrigin
@RestController
@RequestMapping("/login")
public class BookStoreUserController {
	static int otp;
	@Autowired
	IBookStoreUserService bookStoreUserService;
	
	@Autowired 
	private EmailServiceImpl emailService;
	
	@PostMapping("/check")
	public LoginDTO validateUser(@RequestBody LoginDTO login) {
		System.out.println("In post check login");
		String s =  bookStoreUserService.appLogin(login.getUsername(), login.getPassword());
		System.out.println(s+"-----------------");
		login.setRole(s);
		return login;
	}
	@PostMapping("/check/username")
	public LoginDTO validateUserName(@RequestBody LoginDTO login) {
		System.out.println("In post check login");
		String s =  bookStoreUserService.appCheckUser(login.getUsername());
		System.out.println(s+"-----------------");
		if(s!=null) {
			login.setIsUsername(true);
			login.setEmail(s);
		}
		return login;
	}
	
	@PostMapping("/sendmail")
    public String sendMail(@RequestBody MailStructure details)
    {
		System.out.println("email sent");
		otp = (int)(Math.random()*1000000);
		details.setSubject("OTP to set password for BOOK STORE APP");
		details.setMsgBody("Please Enter this OTP to change your Password "+otp);
        String status
            = emailService.sendSimpleMail(details);
 
        return status;
    }
	
	@PutMapping("/changepassword")
	public Boolean resetPassword(@RequestBody ResetPasswordDTO resetPassword ) {
		String otpstr = otp + "";
		if(resetPassword.getOtp().equals(otpstr)) {
			boolean check = bookStoreUserService.appUpdatePassword(resetPassword.getPassword(), resetPassword.getUsername());
			otp = (int)(Math.random()*1000000);
			return check;
		}
		otp = (int)(Math.random()*1000000);
		return false;
	}
}
