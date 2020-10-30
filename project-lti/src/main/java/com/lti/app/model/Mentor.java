package com.lti.app.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mentorId;

	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private String courseExpertise;

	private LocalTime startTime;

	private LocalTime endTime;
	
	private boolean active;

}
