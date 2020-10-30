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

@RestController
public class MentorController {

	@Autowired
	private MentorService mentorService;

	@PostMapping("/mentor")
	public MentorDto createMentor(@RequestBody MentorDto mentorDto) {
		return mentorService.createMentor(mentorDto);
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
