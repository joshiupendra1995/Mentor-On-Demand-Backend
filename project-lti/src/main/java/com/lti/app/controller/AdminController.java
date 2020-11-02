package com.lti.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.app.dto.MentorDto;
import com.lti.app.dto.UserDto;
import com.lti.app.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admin/mentors")
	public List<MentorDto> getMentors(@RequestParam(required = false) String courseName) {
		return adminService.getMentors(courseName);
	}

	@GetMapping("/admin/users")
	public List<UserDto> getAllUsers() {
		return adminService.getAllUsers();
	}

	@PutMapping("/admin/blockUser/{emailId}")
	public UserDto blockUser(@PathVariable String emailId) {
		return adminService.blockUser(emailId);
	}

	@PutMapping("/admin/blockMentor/{emailId}")
	public MentorDto blockMentor(@PathVariable String emailId) {
		return adminService.blockMentor(emailId);
	}
	
	@PutMapping("/admin/unBlockUser/{emailId}")
	public UserDto unBlockUser(@PathVariable String emailId) {
		return adminService.unBlockUser(emailId);
	}

	@PutMapping("/admin/unBlockMentor/{emailId}")
	public MentorDto unBlockMentor(@PathVariable String emailId) {
		return adminService.unBlockMentor(emailId);
	}
	
}
