package com.lti.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorDto implements Serializable{

	private static final long serialVersionUID = 1L;

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
