package com.lti.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorDto {

	private Integer mentorId;

	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private String courseExpertise;
	
	private String startTime;
	
	private String endTime;
	
	private boolean active;
	
	private String roleType;

}
