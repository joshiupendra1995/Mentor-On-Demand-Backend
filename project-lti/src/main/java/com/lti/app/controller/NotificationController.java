package com.lti.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.app.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/sendNotification/{emailId}/{userName}")
	public String sendNotification(@PathVariable String emailId, @PathVariable String userName) {
		return notificationService.sendNotification(emailId, userName);

	}
}
