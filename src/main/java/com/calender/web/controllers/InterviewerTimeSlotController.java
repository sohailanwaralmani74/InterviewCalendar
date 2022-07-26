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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.InterviewerTimeSlotDto;
import com.calender.entities.InterviewerTimeSlot;
import com.calender.exceptions.InterviewerNotFoundException;
import com.calender.services.InterviewerTimeSlotService;
import com.calender.validators.UserValidator;

@RestController
@RequestMapping("/slot")
public class InterviewerTimeSlotController {
	@Autowired
	private InterviewerTimeSlotService timeSlotService;
	@Autowired
	private ModelMapper mapper;

	@GetMapping("/all/{userId}")
	public List<InterviewerTimeSlotDto> getAllTimeSlotForUser(@PathVariable long userId) {

		List<InterviewerTimeSlotDto> responseList = new ArrayList<InterviewerTimeSlotDto>();
		List<InterviewerTimeSlot> userTimeSlots = timeSlotService.getAllTimeSlotsForUser(userId);
		mapper.map(responseList, userTimeSlots);

		return responseList;
	}
	@GetMapping
	public List<InterviewerTimeSlotDto> getAllTimeSlots() {
		List<InterviewerTimeSlotDto> responseList = new ArrayList<InterviewerTimeSlotDto>();
		List<InterviewerTimeSlot> userTimeSlots = timeSlotService.getAllTimeSlots();
		mapper.map(responseList, userTimeSlots);

		return responseList;
	}
	@GetMapping("/{id}")
	public InterviewerTimeSlotDto getTimeSlot(@PathVariable long id) {

		InterviewerTimeSlot serviceResponse = timeSlotService.getTimeSlot(id);
		InterviewerTimeSlotDto responseDto = mapper.map(serviceResponse, InterviewerTimeSlotDto.class);
		return responseDto;
	}
	@PostMapping
	public List<InterviewerTimeSlotDto> addTimeSlot(@RequestBody InterviewerTimeSlotDto timeSlotDto) {
		// check if interviewer is not available at backend
		if(!UserValidator.doesInterviewerExists(timeSlotDto.getInterviewer().getId())) {
			throw new InterviewerNotFoundException();
		}
		List<InterviewerTimeSlotDto> responseList = new ArrayList<InterviewerTimeSlotDto>();
		InterviewerTimeSlot userTimeSlot = mapper.map(timeSlotDto, InterviewerTimeSlot.class);
		List<InterviewerTimeSlot> serviceResponse = timeSlotService.addTimeSlot(userTimeSlot);
		mapper.map(serviceResponse,responseList);
		return responseList;
	}
	@PostMapping("/all")
	public List<InterviewerTimeSlotDto> addTimeSlot(@RequestBody List<InterviewerTimeSlotDto> timeSlotDto) {

		List<InterviewerTimeSlotDto> responseList = new ArrayList<InterviewerTimeSlotDto>();
		List<InterviewerTimeSlot> userTimeSlots = new ArrayList<InterviewerTimeSlot>();
		mapper.map(userTimeSlots,timeSlotDto);
		List<InterviewerTimeSlot> serviceResponseList = timeSlotService.addTimeSlot(userTimeSlots);
		mapper.map(serviceResponseList,responseList);
		return responseList;
	}

	@PutMapping
	public List<InterviewerTimeSlotDto> updateTimeSlot(@RequestBody InterviewerTimeSlotDto timeSlotDto) {
		List<InterviewerTimeSlotDto> responseList = new ArrayList<InterviewerTimeSlotDto>();
		InterviewerTimeSlot userTimeSlot = mapper.map(timeSlotDto, InterviewerTimeSlot.class);
		List<InterviewerTimeSlot> serviceResponseList = timeSlotService.addTimeSlot(userTimeSlot);
		mapper.map(serviceResponseList,responseList);
		return responseList;
	}

	@DeleteMapping("/{id}")
	public CustomAPIResponse deleteTimeSlot(@PathVariable long id) {
		CustomAPIResponse apiResponse = new CustomAPIResponse();
		try {
			timeSlotService.deleteTimeSlot(id);
			apiResponse.setCode("200");
			apiResponse.setStatus("SUCCESS");
			apiResponse.setMessage("Timeslot with id:" + id + " Deleted Successfully");
		} catch (Exception e) {
			apiResponse.setCode("500");
			apiResponse.setStatus("FAILURE");
			apiResponse.setMessage("Error while deleting Timeslot with id:" + id);
		}
		return apiResponse;
	}

}
