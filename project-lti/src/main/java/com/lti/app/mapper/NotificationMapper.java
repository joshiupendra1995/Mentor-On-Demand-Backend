package com.lti.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lti.app.dto.NotificationDto;
import com.lti.app.model.Notification;

@Component
public class NotificationMapper {

	public Notification getModel(NotificationDto dto) {
		return new Notification(dto.getEmailId(), dto.getUserName(), dto.getMentorName(), dto.getCourseName());
	}

	public NotificationDto getBO(Notification notification) {
		return new NotificationDto(notification.getEmailId(), notification.getUserName(), notification.getMentorName(),
				notification.getCourseName());
	}

	public List<NotificationDto> getBOList(List<Notification> list) {
		List<NotificationDto> dtoList = new ArrayList<>();
		list.forEach(notification -> {
			dtoList.add(getBO(notification));
		});
		return dtoList;
	}
}
