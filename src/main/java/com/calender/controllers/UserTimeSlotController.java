package com.calender.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.AvailableSlotsRequest;
import com.calender.dtos.AvailableTimeSlot;
import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.UserTimeSlotDto;
import com.calender.services.UserTimeSlotService;

@RestController
@RequestMapping("/api/CandidateSlot")
public class UserTimeSlotController {
	@Autowired
	private UserTimeSlotService timeSlotService;
	

	@GetMapping
	public List<UserTimeSlotDto> getAllTimeSlots() {
		return timeSlotService.findAll();

	}
	
	@PostMapping("/availableSlots")
	public List<AvailableTimeSlot> getAvailableSlots(@Valid @RequestBody AvailableSlotsRequest request){
		return timeSlotService.getAvaialbleTimeSlots(request);
	}

	@PostMapping
	public CustomAPIResponse addTimeSlot(@Valid @RequestBody UserTimeSlotDto userTimeSlot) {
		return timeSlotService.addTimeSlot(userTimeSlot);
	}

	@PutMapping("/{id}")
	public CustomAPIResponse updateTimeSlot(@Valid @RequestBody UserTimeSlotDto userTimeSlot, @PathVariable long id) {
		return timeSlotService.updateTimeSlot(userTimeSlot, id);
	}

	@DeleteMapping("/{id}")
	public CustomAPIResponse deleteTimeSlot(@PathVariable long id) {
			return timeSlotService.deleteTimeSlot(id);
	}

}
