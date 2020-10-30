package com.lti.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lti.app.constants.Constant;
import com.lti.app.dto.UserDto;
import com.lti.app.model.User;

@Component
public class UserMapper {

	public UserDto getBO(User user) {
		return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmailId(),
				user.getPassword(), user.isActive(), Constant.USER);
	}

	public User getModel(UserDto userDto) {
		return new User(userDto.getUserId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmailId(),
				userDto.getPassword(), Boolean.TRUE);
	}

	public User getModelForAdmin(UserDto userDto) {
		return new User(userDto.getUserId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmailId(),
				userDto.getPassword(), userDto.isActive());
	}

	public List<UserDto> getBOList(List<User> userList) {
		List<UserDto> userDtoList = new ArrayList<>();
		userList.forEach(user -> {
			userDtoList.add(getBO(user));
		});
		return userDtoList;
	}

	public List<User> getModelList(List<UserDto> userDtoList) {
		List<User> userList = new ArrayList<>();
		userDtoList.forEach(userDto -> {
			userList.add(getModel(userDto));
		});
		return userList;
	}

}
