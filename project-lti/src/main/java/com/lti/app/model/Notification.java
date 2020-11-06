package com.lti.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

	@Id
	private String emailId;

	private String userName;
	
	private String mentorName;
	
	private String courseName;
}