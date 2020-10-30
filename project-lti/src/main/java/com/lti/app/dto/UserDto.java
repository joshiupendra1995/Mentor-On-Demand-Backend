package com.lti.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{

	private static final long serialVersionUID = 2L;

	private Integer userId;

	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private boolean active;
	
	private String roleType;
}
