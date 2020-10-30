package com.lti.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Integer userId;

	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private boolean active;
	
	private String roleType;
}
