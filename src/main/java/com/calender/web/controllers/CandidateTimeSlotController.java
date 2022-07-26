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
import com.calender.dtos.CandidateTimeSlotDto;
import com.calender.entities.CandidateTimeSlot;
import com.calender.exceptions.CandidateNotFoundException;
import com.calender.services.CandidateTimeSlotService;
import com.calender.validators.UserValidator;

@RestController
@RequestMapping("/api/CandidateSlot")
public class CandidateTimeSlotController {
	@Autowired
	private CandidateTimeSlotService timeSlotService;
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<CandidateTimeSlotDto> getAllTimeSlots() {
		List<CandidateTimeSlotDto> responseList = new ArrayList<CandidateTimeSlotDto>();
		List<CandidateTimeSlot> userTimeSlots = timeSlotService.getAllTimeSlots();
		mapper.map(responseList, userTimeSlots);

		return responseList;
	}
	@GetMapping("/{id}")
	public CandidateTimeSlotDto getTimeSlot(@PathVariable long id) {

		CandidateTimeSlot serviceResponse = timeSlotService.getTimeSlot(id);
		CandidateTimeSlotDto responseDto = mapper.map(serviceResponse, CandidateTimeSlotDto.class);
		return responseDto;
	}
	@PostMapping
	public List<CandidateTimeSlotDto> addTimeSlot(@RequestBody CandidateTimeSlotDto timeSlotDto) {
		// check if Candidate is not available at backend
		if(!UserValidator.doesCandidateExists(timeSlotDto.getCandidate().getId())) {
			throw new CandidateNotFoundException();
		}
		List<CandidateTimeSlotDto> responseList = new ArrayList<CandidateTimeSlotDto>();
		CandidateTimeSlot userTimeSlot = mapper.map(timeSlotDto, CandidateTimeSlot.class);
		List<CandidateTimeSlot> serviceResponse = timeSlotService.addTimeSlot(userTimeSlot);
		mapper.map(serviceResponse,responseList);
		return responseList;
	}
	@PostMapping("/all")
	public List<CandidateTimeSlotDto> addTimeSlot(@RequestBody List<CandidateTimeSlotDto> timeSlotDto) {

		List<CandidateTimeSlotDto> responseList = new ArrayList<CandidateTimeSlotDto>();
		List<CandidateTimeSlot> userTimeSlots = new ArrayList<CandidateTimeSlot>();
		mapper.map(userTimeSlots,timeSlotDto);
		List<CandidateTimeSlot> serviceResponseList = timeSlotService.addTimeSlot(userTimeSlots);
		mapper.map(serviceResponseList,responseList);
		return responseList;
	}

	@PutMapping
	public List<CandidateTimeSlotDto> updateTimeSlot(@RequestBody CandidateTimeSlotDto timeSlotDto) {
		List<CandidateTimeSlotDto> responseList = new ArrayList<CandidateTimeSlotDto>();
		CandidateTimeSlot userTimeSlot = mapper.map(timeSlotDto, CandidateTimeSlot.class);
		List<CandidateTimeSlot> serviceResponseList = timeSlotService.addTimeSlot(userTimeSlot);
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
