package com.lti.app.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lti.app.dto.UserDto;
import com.lti.app.service.MentorService;
import com.lti.app.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private MentorService mentorService;

	@GetMapping("/member/login/{emailId}/{password}")
	public Object validateMember(@PathVariable String emailId, @PathVariable String password) {
		UserDto userDto = userService.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(userDto)) 
			return mentorService.findByEmailIdAndPassword(emailId, password);
		return userDto;
		
	}
}
