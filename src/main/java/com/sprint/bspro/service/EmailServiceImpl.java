package com.sprint.bspro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sprint.bspro.entity.MailStructure;
@Service
public class EmailServiceImpl implements IEmailService {

	@Autowired private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
    
	@Override
	public String sendSimpleMail(MailStructure details) {
		try {
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }
	}

}
