package com.calender.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.InterviewerDto;
import com.calender.dtos.CustomAPIResponse;
import com.calender.entities.Interviewer;
import com.calender.services.InterviewerService;

@RestController("/interviewer")
public class InterviewerController {
	
	@Autowired
	private InterviewerService userService;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<InterviewerDto> getAllUsers() {
		List<InterviewerDto> usersList = new ArrayList<InterviewerDto>();
		List<Interviewer> applicationUsersList = userService.getAllUsers();
		mapper.map(usersList,applicationUsersList);
		return usersList;
	}
	@GetMapping("/{id}")
	public InterviewerDto getUser(@PathVariable long id) {
		Interviewer serviceResponse = userService.getUser(id);
		InterviewerDto responseDto = mapper.map(serviceResponse, InterviewerDto.class);
		return responseDto;
	}
	@PostMapping
	public InterviewerDto addUser(@RequestBody InterviewerDto userDto) {

		Interviewer user = mapper.map(userDto, Interviewer.class);
		Interviewer serviceResponse = userService.addUser(user);
		InterviewerDto responseDto = mapper.map(serviceResponse, InterviewerDto.class);
		return responseDto;
	}
	@PostMapping("/all")
	public List<InterviewerDto> addUser(@RequestBody List<InterviewerDto> usersDto) {

		List<InterviewerDto> responseList = new ArrayList<InterviewerDto>();
		List<Interviewer> users = new ArrayList<Interviewer>();
		mapper.map(users,usersDto);
		List<Interviewer> serviceResponseList = userService.addUser(users);
		mapper.map(serviceResponseList,responseList);
		return responseList;
	}
	@PutMapping
	public InterviewerDto updateUser(@RequestBody InterviewerDto userDto) {

		Interviewer user = mapper.map(userDto, Interviewer.class);
		Interviewer serviceResponse = userService.addUser(user);
		InterviewerDto responseDto = mapper.map(serviceResponse, InterviewerDto.class);
		return responseDto;
	}
	
	@DeleteMapping("/{id}")
	public CustomAPIResponse deleteTimeSlot(@PathVariable long id) {
		CustomAPIResponse apiResponse = new CustomAPIResponse();
		try {
			userService.deleteUser(id);
			apiResponse.setCode("200");
			apiResponse.setStatus("SUCCESS");
			apiResponse.setMessage("User with id:" + id + " Deleted Successfully");
		} catch (Exception e) {
			apiResponse.setCode("500");
			apiResponse.setStatus("FAILURE");
			apiResponse.setMessage("Error while deleting user with id:" + id);
		}
		return apiResponse;
	}

}
