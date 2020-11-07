package com.lti.app.service;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lti.app.constants.Constant;
import com.lti.app.dto.UserDto;
import com.lti.app.exception.InvalidUserException;
import com.lti.app.mapper.UserMapper;
import com.lti.app.model.User;
import com.lti.app.repository.UserRepository;

import javassist.bytecode.DuplicateMemberException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserMapper userMapper;

	public UserDto createUser(UserDto userDto) throws DuplicateMemberException {
		if (userRepo.findByEmailId(userDto.getEmailId()) != null) {
			throw new DuplicateMemberException(Constant.DUPLICATE_MEMBER);
		}
		return userMapper.getBO(userRepo.save(userMapper.getModel(userDto)));

	}

	public UserDto updateUser(UserDto userDto) {

		return userMapper.getBO(userRepo.save(userMapper.getModel(userDto)));

	}

	public List<UserDto> getAllUsers() {
		List<User> userList = userRepo.findAll();
		if (userList.isEmpty()) {
			throw new EmptyResultDataAccessException("No record found", 1);// expected atleast one record
		} else {
			return userMapper.getBOList(userList);
		}
	}

	public UserDto findByEmailId(String emailId) {
		User user = userRepo.findByEmailId(emailId);
		if (Objects.isNull(user)) {
			throw new InvalidUserException(Constant.INVALID_USR_MSG);
		}
		return userMapper.getBO(user);
	}

	public UserDto blockUser(UserDto userDto) {
		return userMapper.getBO(userRepo.save(userMapper.getModelForAdmin(userDto)));
	}

	public UserDto findByEmailIdAndPassword(String emailId, String password) {
		User user = userRepo.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(user)) {
			return null;
		}
		return userMapper.getBO(user);

	}

	public UserDto unBlockUser(UserDto userDto) {
		return userMapper.getBO(userRepo.save(userMapper.getModelForAdmin(userDto)));
	}

}
