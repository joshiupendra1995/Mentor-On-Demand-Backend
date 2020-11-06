package com.lti.app.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.app.dto.EmailDto;
import com.lti.app.service.NotificationService;

import freemarker.template.TemplateException;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/sendNotification")
	public String sendNotification(@RequestBody EmailDto emailDto)
			throws MessagingException, IOException, TemplateException {
		return notificationService.sendNotification(emailDto.getUserName(), emailDto.getMentorName(),
				emailDto.getCourseName(),emailDto.getEmailId());

	}
}
