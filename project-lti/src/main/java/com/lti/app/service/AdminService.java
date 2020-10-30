package com.lti.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.app.dto.MentorDto;
import com.lti.app.dto.UserDto;
import com.lti.app.exception.InvalidUserException;

@Service
public class AdminService {

	@Autowired
	private UserService userService;

	@Autowired
	private MentorService mentorService;
	

	public List<MentorDto> getMentors(String courseName) {
		return mentorService.getMentors(courseName);
	}

	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	public UserDto blockUser(String emailId) {
		UserDto userDto = userService.findByEmailId(emailId);
		if (userDto == null) {
			throw new InvalidUserException("invalid user");
		}
		userDto.setActive(false);
		return userService.blockUser(userDto);
	}

	public MentorDto blockMentor(String emailId) {
		MentorDto mentorDto = mentorService.findByEmailId(emailId);
		if (mentorDto == null) {
			throw new InvalidUserException("invalid mentor");
		}
		mentorDto.setActive(false);
		return mentorService.blockMentor(mentorDto);
	}

}
