package com.calender.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.AvailableSlotsRequest;
import com.calender.dtos.TimeSlotRequestDto;
import com.calender.services.UserTimeSlotServiceImpl;

/**
 * @author Sohail Anwar
 * 
 *         <blockquote> UserTimeSlotController
 * 
 *         UserTimeSlotController is handler for CRUD request for TimeSlot and
 *         handle below request.
 * 
 *         find all time slots including interviewers and candidates, find all
 *         available time slots with candidate and interviewers ids. add time
 *         slot for user update time slot delete time slot. 
 */

@RestController
@RequestMapping("/api/v1/interviewSlots")
public class UserTimeSlotController {
	

	private UserTimeSlotServiceImpl timeSlotService;
	
	@Autowired
	public UserTimeSlotController(UserTimeSlotServiceImpl timeSlotService) {
		this.timeSlotService = timeSlotService;
	}

	@PostMapping("/availableSlots")
	public ResponseEntity<?> getAvailableSlots(@Valid @RequestBody AvailableSlotsRequest request) {
		return new ResponseEntity<>(timeSlotService.getAvaialbleTimeSlots(request), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addTimeSlot(@Valid @RequestBody TimeSlotRequestDto userTimeSlot) {
		return new ResponseEntity<>(timeSlotService.addTimeSlot(userTimeSlot), HttpStatus.CREATED);
	}

}
