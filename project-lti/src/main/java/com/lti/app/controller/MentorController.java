package com.lti.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.app.dto.MentorDto;
import com.lti.app.service.MentorService;

import javassist.bytecode.DuplicateMemberException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MentorController {

	@Autowired
	private MentorService mentorService;

	@PostMapping("/mentor")
	public MentorDto createMentor(@RequestBody MentorDto mentorDto) throws DuplicateMemberException {
		try {
			return mentorService.createMentor(mentorDto);
		} catch (DuplicateMemberException e) {
			log.error("updation failed{}", e);
			throw e;
		}
	}

	@PutMapping("/mentor")
	public MentorDto updateMentor(@RequestBody MentorDto mentorDto) {
		return mentorService.updateMentor(mentorDto);
	}

	@GetMapping("/mentors")
	public List<MentorDto> getMentors(@RequestParam(required = false) String courseName) {
		return mentorService.getMentors(courseName).stream().filter(MentorDto::isActive).collect(Collectors.toList());// filtered
																														// active
																														// mentors
	}

}
