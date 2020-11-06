package com.lti.app.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.app.constants.Constant;
import com.lti.app.dto.AdminDto;
import com.lti.app.dto.MentorDto;
import com.lti.app.dto.UserDto;
import com.lti.app.exception.InvalidUserException;
import com.lti.app.mapper.AdminMapper;
import com.lti.app.model.Admin;
import com.lti.app.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private UserService userService;

	@Autowired
	private MentorService mentorService;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	public List<MentorDto> getMentors(String courseName) {
		return mentorService.getMentors(courseName);
	}

	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	public UserDto blockUser(String emailId) {
		UserDto userDto = userService.findByEmailId(emailId);
		if (Objects.isNull(userDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		userDto.setActive(false);
		return userService.blockUser(userDto);
	}

	public MentorDto blockMentor(String emailId) {
		MentorDto mentorDto = mentorService.findByEmailId(emailId);
		if (Objects.isNull(mentorDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		mentorDto.setActive(false);
		return mentorService.blockMentor(mentorDto);
	}

	public UserDto unBlockUser(String emailId) {
		UserDto userDto = userService.findByEmailId(emailId);
		if (Objects.isNull(userDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		userDto.setActive(true);
		return userService.unBlockUser(userDto);
	}

	public MentorDto unBlockMentor(String emailId) {
		MentorDto mentorDto = mentorService.findByEmailId(emailId);
		if (Objects.isNull(mentorDto)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		mentorDto.setActive(true);
		return mentorService.unBlockMentor(mentorDto);
	}

	public AdminDto findByEmailIdAndPassword(String emailId, String password) {
		Admin admin = adminRepository.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(admin)) {
			throw new InvalidUserException(Constant.INVALID_USR_MSG);
		}
		return adminMapper.getBO(admin);
	}

}
