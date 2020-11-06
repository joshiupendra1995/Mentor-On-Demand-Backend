package com.lti.app.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.app.dto.NotificationDto;
import com.lti.app.service.NotificationService;

import freemarker.template.TemplateException;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/sendNotification")
	public String sendNotification(@RequestBody NotificationDto notificationDto)
			throws MessagingException, IOException, TemplateException {
		return notificationService.sendNotification(notificationDto);

	}
	
	@GetMapping("/getNotificationDetails/{emailId}")
	public List<NotificationDto> getNotificationDetails(@PathVariable String emailId) {
		return notificationService.getNotificationDetails(emailId);
	}
}
