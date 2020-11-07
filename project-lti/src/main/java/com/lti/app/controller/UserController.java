package com.lti.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.app.dto.UserDto;
import com.lti.app.service.UserService;

import javassist.bytecode.DuplicateMemberException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers().stream().filter(UserDto::isActive).collect(Collectors.toList());// filtered
																											// active
																											// users
	}

	@PostMapping("/user")
	public UserDto createUser(@RequestBody UserDto userDto) throws DuplicateMemberException {
		try {
			return userService.createUser(userDto);
		} catch (DuplicateMemberException e) {
			log.error("creation failed{}", e);
			throw e;
		}
	}

	@PutMapping("/user")
	public UserDto updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

}
