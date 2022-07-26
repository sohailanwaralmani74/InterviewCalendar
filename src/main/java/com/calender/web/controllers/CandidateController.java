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

import com.calender.dtos.CandidateDto;
import com.calender.dtos.CustomAPIResponse;
import com.calender.entities.Candidate;
import com.calender.services.CandidateService;

@RestController("/candidate")
public class CandidateController {
	
	@Autowired
	private CandidateService userService;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<CandidateDto> getAllUsers() {
		List<CandidateDto> usersList = new ArrayList<CandidateDto>();
		List<Candidate> applicationUsersList = userService.getAllUsers();
		mapper.map(usersList,applicationUsersList);
		return usersList;
	}
	@GetMapping("/{id}")
	public CandidateDto getUser(@PathVariable long id) {
		Candidate serviceResponse = userService.getUser(id);
		CandidateDto responseDto = mapper.map(serviceResponse, CandidateDto.class);
		return responseDto;
	}
	@PostMapping
	public CandidateDto addUser(@RequestBody CandidateDto userDto) {

		Candidate user = mapper.map(userDto, Candidate.class);
		Candidate serviceResponse = userService.addUser(user);
		CandidateDto responseDto = mapper.map(serviceResponse, CandidateDto.class);
		return responseDto;
	}
	@PostMapping("/all")
	public List<CandidateDto> addUser(@RequestBody List<CandidateDto> usersDto) {

		List<CandidateDto> responseList = new ArrayList<CandidateDto>();
		List<Candidate> users = new ArrayList<Candidate>();
		mapper.map(users,usersDto);
		List<Candidate> serviceResponseList = userService.addUser(users);
		mapper.map(serviceResponseList,responseList);
		return responseList;
	}
	@PutMapping
	public CandidateDto updateUser(@RequestBody CandidateDto userDto) {

		Candidate user = mapper.map(userDto, Candidate.class);
		Candidate serviceResponse = userService.addUser(user);
		CandidateDto responseDto = mapper.map(serviceResponse, CandidateDto.class);
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
