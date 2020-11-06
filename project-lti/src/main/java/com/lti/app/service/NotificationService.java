package com.lti.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public String sendNotification(String emailId,String userName) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("Admin:noreply");
		msg.setTo(emailId);

		msg.setSubject("Admin:Meeting Scheduled");
		msg.setText("Hi" + userName + "\n Your meeting is scheduled");
		javaMailSender.send(msg);
		return "Message Sent!!";
	}

}
