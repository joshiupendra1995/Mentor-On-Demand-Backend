package com.lti.app.mapper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lti.app.constants.Constant;
import com.lti.app.dto.MentorDto;
import com.lti.app.model.Mentor;

@Component
public class MentorMapper {

	public MentorDto getBO(Mentor mentor) {
		return new MentorDto(mentor.getMentorId(), mentor.getFirstName(), mentor.getLastName(), mentor.getEmailId(),
				mentor.getPassword(), mentor.getCourseExpertise(), String.valueOf(mentor.getStartTime()),
				String.valueOf(mentor.getEndTime()), mentor.isActive(), Constant.MENTOR);
	}

	public Mentor getModel(MentorDto mentorDto) {
		return new Mentor(mentorDto.getMentorId(), mentorDto.getFirstName(), mentorDto.getLastName(),
				mentorDto.getEmailId(), mentorDto.getPassword(), mentorDto.getCourseExpertise(),
				LocalTime.parse(mentorDto.getStartTime()), LocalTime.parse(mentorDto.getEndTime()), Boolean.TRUE);
	}

	public Mentor getModelForAdmin(MentorDto mentorDto) {
		return new Mentor(mentorDto.getMentorId(), mentorDto.getFirstName(), mentorDto.getLastName(),
				mentorDto.getEmailId(), mentorDto.getPassword(), mentorDto.getCourseExpertise(),
				LocalTime.parse(mentorDto.getStartTime()), LocalTime.parse(mentorDto.getEndTime()),
				mentorDto.isActive());
	}

	public List<MentorDto> getBOList(List<Mentor> mentorList) {
		List<MentorDto> mentorDtoList = new ArrayList<>();
		mentorList.forEach(mentor -> mentorDtoList.add(getBO(mentor)));

		return mentorDtoList;
	}

	public List<Mentor> getModelList(List<MentorDto> mentoDtoList) {
		List<Mentor> mentorList = new ArrayList<>();
		mentoDtoList.forEach(mentorDto -> mentorList.add(getModel(mentorDto)));

		return mentorList;
	}
}
