package com.lti.app.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lti.app.dto.NotificationDto;
import com.lti.app.mapper.NotificationMapper;
import com.lti.app.model.Notification;
import com.lti.app.repository.NotificationRepository;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private NotificationMapper mapper;

	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;

	@Value("${spring.mail.username}")
	private String from;

	public NotificationDto sendNotification(NotificationDto notificationDto)
			throws MessagingException, IOException, TemplateException {

		Map<String, String> model = new HashMap<>();
		model.put("userName", notificationDto.getUserName());
		model.put("location", "Pune,Maharashtra");
		model.put("content", "You have your " + notificationDto.getCourseName() + " course scheduled with "
				+ notificationDto.getMentorName() + "\nplease get in touch with him.");
		model.put("signature", "ADMIN");
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		Template template = emailConfig.getTemplate("email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		mimeMessageHelper.setSubject("Admin Notification:Training Scheduled");
		mimeMessageHelper.setText(html, true);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setReplyTo(notificationDto.getEmailId());
		mimeMessageHelper.setTo(notificationDto.getEmailId());
		javaMailSender.send(message);
		return mapper.getBO(notificationRepository.save(mapper.getModel(notificationDto)));
	}

	public List<NotificationDto> getNotificationDetails(String emailId) {
		List<Notification> list = notificationRepository.findByEmailId(emailId);
		if (list == null || list.isEmpty()) {
			throw new EmptyResultDataAccessException("No Record Found", 1);
		}
		return mapper.getBOList(list);

	}

	public void sendNotification(String emailId, String firstName)
			throws MessagingException, IOException, TemplateException {
		Map<String, String> model = new HashMap<>();
		model.put("userName", firstName);
		model.put("location", "Pune,Maharashtra");
		model.put("content", "\nYou are blocked by the admin due to some inactivity from your side.");
		model.put("signature", "ADMIN");
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		Template template = emailConfig.getTemplate("email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		mimeMessageHelper.setSubject("Admin Notification:Blocked You");
		mimeMessageHelper.setText(html, true);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setReplyTo(emailId);
		mimeMessageHelper.setTo(emailId);
		javaMailSender.send(message);
	}

	public void sendUnblockedNotification(String emailId, String firstName)
			throws MessagingException, IOException, TemplateException {
		Map<String, String> model = new HashMap<>();
		model.put("userName", firstName);
		model.put("location", "Pune,Maharashtra");
		model.put("content", "\nYou are unblocked by the admin , you can now login to the portal.");
		model.put("signature", "ADMIN");
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		Template template = emailConfig.getTemplate("email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		mimeMessageHelper.setSubject("Admin Notification:Blocked You");
		mimeMessageHelper.setText(html, true);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setReplyTo(emailId);
		mimeMessageHelper.setTo(emailId);
		javaMailSender.send(message);
	}

}
