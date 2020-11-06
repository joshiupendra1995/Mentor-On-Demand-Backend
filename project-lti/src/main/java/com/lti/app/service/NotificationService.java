package com.lti.app.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StreamUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;

	@Value("${spring.mail.username}")
	private String from;

	@Value("classpath:static/images/LTI.png")
	private Resource image;

	public String sendNotification(String userName, String mentorName, String courseName, String emailId)
			throws MessagingException, IOException, TemplateException {

		Map<String, String> model = new HashMap<>();
		model.put("userName", userName);
		model.put("location", "Pune,Maharashtra");
		model.put("content", "You have your " + courseName + " course scheduled with " + mentorName
				+ "\nplease get in touch with him.");
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
		mimeMessageHelper.setReplyTo(emailId);
		mimeMessageHelper.setTo(emailId);
		javaMailSender.send(message);
		return "Message Sent!!";
	}

}
