package com.lti.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
	
	private Integer adminId;
	
	private String firstName;

	private String lastName;

	private String emailId;

	private String password; 
	
	private boolean active;
}
