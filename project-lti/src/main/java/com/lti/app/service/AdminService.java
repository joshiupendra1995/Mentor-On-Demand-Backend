package com.lti.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.app.constants.Constant;
import com.lti.app.dto.AdminDto;
import com.lti.app.dto.MentorDto;
import com.lti.app.dto.NotificationDto;
import com.lti.app.dto.UserDto;
import com.lti.app.exception.InvalidUserException;
import com.lti.app.mapper.AdminMapper;
import com.lti.app.model.Admin;
import com.lti.app.repository.AdminRepository;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {

	@Autowired
	private UserService userService;

	@Autowired
	private MentorService mentorService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private NotificationService notificationService;

	public List<MentorDto> getMentors(String courseName) {
		return mentorService.getMentors(courseName);
	}

	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	public UserDto blockUser(String emailId) throws MessagingException, IOException, TemplateException {
		UserDto userDto = userService.findByEmailId(emailId);
		if (Objects.isNull(userDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		userDto.setActive(false);
		UserDto user = userService.blockUser(userDto);
		log.info("user blocked{}", user);
		notificationService.sendNotification(user.getEmailId(), user.getFirstName());
		return user;
	}

	public MentorDto blockMentor(String emailId) throws MessagingException, IOException, TemplateException {
		MentorDto mentorDto = mentorService.findByEmailId(emailId);
		if (Objects.isNull(mentorDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		mentorDto.setActive(false);
		MentorDto mentor = mentorService.blockMentor(mentorDto);
		log.info("mentor blocked{}", mentor);
		notificationService.sendNotification(mentor.getEmailId(), mentor.getFirstName());
		return mentor;
	}

	public UserDto unBlockUser(String emailId) throws MessagingException, IOException, TemplateException {
		UserDto userDto = userService.findByEmailId(emailId);
		if (Objects.isNull(userDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		userDto.setActive(true);
		UserDto user = userService.unBlockUser(userDto);
		log.info("user unbocked{}", user);
		notificationService.sendUnblockedNotification(user.getEmailId(), user.getFirstName());
		return user;
	}

	public MentorDto unBlockMentor(String emailId) throws MessagingException, IOException, TemplateException {
		MentorDto mentorDto = mentorService.findByEmailId(emailId);
		if (Objects.isNull(mentorDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		mentorDto.setActive(true);
		MentorDto mentor = mentorService.unBlockMentor(mentorDto);
		log.info("mentor unblocked{}", mentor);
		notificationService.sendUnblockedNotification(mentor.getEmailId(), mentor.getFirstName());
		return mentor;
	}

	public AdminDto findByEmailIdAndPassword(String emailId, String password) {
		Admin admin = adminRepository.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(admin)) {
			throw new InvalidUserException(Constant.INVALID_USR_MSG);
		}
		return adminMapper.getBO(admin);
	}

}
