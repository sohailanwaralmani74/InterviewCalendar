package com.calender.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.TimeSlotRequestDto;
import com.calender.services.UserTimeSlotService;

/**
 * @author Sohail Anwar
 * 
 *         UserTimeSlotController
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

	private UserTimeSlotService timeSlotService;
	
	@Autowired
	public UserTimeSlotController(UserTimeSlotService timeSlotService) {
		this.timeSlotService = timeSlotService;
	}
	@GetMapping("/availableSlots/{candidateId}")
	public ResponseEntity<?> getAvailableSlots(@RequestParam List<String> interviewers,
			@PathVariable long candidateId) {
		return new ResponseEntity<>(timeSlotService.getAvaialbleTimeSlots(candidateId, interviewers),
				HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<?> addTimeSlot(@Valid @RequestBody TimeSlotRequestDto userTimeSlot) {
		return new ResponseEntity<>(timeSlotService.addTimeSlot(userTimeSlot), HttpStatus.CREATED);
	}
}
