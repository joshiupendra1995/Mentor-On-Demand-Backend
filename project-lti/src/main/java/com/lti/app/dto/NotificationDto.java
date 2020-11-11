package com.lti.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

	private Integer id;

	private String mentorId;

	private String userId;

	private String userName;

	private String mentorName;

	private String courseName;
	
	private boolean flag;
}
