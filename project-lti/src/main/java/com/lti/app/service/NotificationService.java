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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StreamUtils;

import com.lti.app.dto.NotificationDto;
import com.lti.app.mapper.NotificationMapper;
import com.lti.app.model.Notification;
import com.lti.app.repository.NotificationRepository;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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

	@Value("classpath:static/images/LTI.png")
	private Resource image;

	public String sendNotification(NotificationDto notificationDto)
			throws MessagingException, IOException, TemplateException {

		Map<String, String> model = new HashMap<>();
		model.put("userName", notificationDto.getUserName());
		model.put("location", "Pune,Maharashtra");
		model.put("content", "You have your " + notificationDto.getCourseName() + " course scheduled with "
				+ notificationDto.getMentorName() + "\nplease get in touch with him.");
		model.put("signature", "ADMIN");
		model.put("logo.png", image.getFilename());

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		byte[] byteSteam = StreamUtils.copyToByteArray(image.getInputStream());
		final InputStreamSource imageSource = new ByteArrayResource(byteSteam);
		mimeMessageHelper.addInline("logo.png", imageSource, MimeTypeUtils.IMAGE_PNG_VALUE);

		Template template = emailConfig.getTemplate("email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

		mimeMessageHelper.setSubject("NoReply:Course Scheduled");
		mimeMessageHelper.setText(html, true);
		mimeMessageHelper.setFrom(from);
		mimeMessageHelper.setReplyTo(notificationDto.getEmailId());
		mimeMessageHelper.setTo(notificationDto.getEmailId());
		javaMailSender.send(message);
		notificationRepository.save(mapper.getModel(notificationDto));
		return "Message Sent!!";
	}

	public List<NotificationDto> getNotificationDetails(String emailId) {
		List<Notification> list = notificationRepository.findByEmailId(emailId);
		if (list == null || list.isEmpty()) {
			throw new EmptyResultDataAccessException("No Record Found", 1);
		}
		return mapper.getBOList(list);

	}

}
