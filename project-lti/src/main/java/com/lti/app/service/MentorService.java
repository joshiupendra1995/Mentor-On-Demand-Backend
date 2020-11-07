package com.lti.app.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.app.constants.Constant;
import com.lti.app.dto.MentorDto;
import com.lti.app.exception.InvalidUserException;
import com.lti.app.mapper.MentorMapper;
import com.lti.app.model.Mentor;
import com.lti.app.repository.MentorRepository;

import javassist.bytecode.DuplicateMemberException;

@Service
public class MentorService {

	@Autowired
	private MentorMapper mentorMapper;

	@Autowired
	private MentorRepository mentorRepo;

	public List<MentorDto> getMentors(String courseName) {
		if (StringUtils.isBlank(courseName)) {
			List<Mentor> mentorList = mentorRepo.findAll();
			if (mentorList.isEmpty()) {
				throw new EmptyResultDataAccessException("No record found", 1);
			}
			return mentorMapper.getBOList(mentorList);
		}
		return mentorMapper.getBOList(mentorRepo.findByCourseExpertiseContainingIgnoreCase(courseName));
	}

	public MentorDto createMentor(MentorDto mentorDto) throws DuplicateMemberException {
		if (mentorRepo.findByEmailId(mentorDto.getEmailId()) != null) {
             throw new DuplicateMemberException(Constant.DUPLICATE_MEMBER);
		}
		return mentorMapper.getBO(mentorRepo.save(mentorMapper.getModel(mentorDto)));
	}

	public MentorDto updateMentor(MentorDto mentorDto) {
		return mentorMapper.getBO(mentorRepo.save(mentorMapper.getModel(mentorDto)));
	}

	public MentorDto findByEmailId(String emailId) {
		Mentor mentor = mentorRepo.findByEmailId(emailId);
		if (Objects.isNull(mentor)) {
			throw new InvalidUserException(Constant.INVALID_USR);
		}
		return mentorMapper.getBO(mentor);
	}

	public MentorDto blockMentor(MentorDto mentorDto) {
		return mentorMapper.getBO(mentorRepo.save(mentorMapper.getModelForAdmin(mentorDto)));
	}

	public MentorDto findByEmailIdAndPassword(String emailId, String password) {
		Mentor mentor = mentorRepo.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(mentor)) {
			return null;
		}
		return mentorMapper.getBO(mentor);
	}

	public MentorDto unBlockMentor(MentorDto mentorDto) {
		return mentorMapper.getBO(mentorRepo.save(mentorMapper.getModelForAdmin(mentorDto)));
	}
}
