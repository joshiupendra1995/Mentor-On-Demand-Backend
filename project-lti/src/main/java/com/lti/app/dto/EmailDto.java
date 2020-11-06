package com.lti.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
	
	private String emailId;

	private String userName;
	
	private String mentorName;
	
	private String courseName;
}
