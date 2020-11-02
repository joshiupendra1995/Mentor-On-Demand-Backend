package com.lti.app.mapper;

import org.springframework.stereotype.Component;

import com.lti.app.dto.AdminDto;
import com.lti.app.model.Admin;

@Component
public class AdminMapper {

	public AdminDto getBO(Admin admin) {
		return new AdminDto(admin.getAdminId(), admin.getFirstName(), admin.getLastName(), admin.getEmailId(),
				admin.getPassword(), Boolean.TRUE);
	}
}
