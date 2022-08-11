package com.calendar.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.calender.dtos.AvailableSlotsRequest;
import com.calender.dtos.AvailableTimeSlot;
import com.calender.entities.ApplicationUser;
import com.calender.entities.UserTimeSlot;
import com.calender.repositories.UserTimeSlotRepository;
import com.calender.services.UserTimeSlotServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class UserTimeSlotTest {

	@Mock
	private UserTimeSlotRepository repository;
	
	@InjectMocks
	private UserTimeSlotServiceImpl service;
	
	@Test
	void testGetAvailableSlots() {
		AvailableSlotsRequest request = AvailableSlotsRequest.builder()
				.candidateId(1L)
				.interviewersIds(Arrays.asList(2L,3L))
				.build();
		
		List<AvailableTimeSlot> availableTimeSlots = Arrays.asList(
				AvailableTimeSlot.builder()
				.day("Thursday")
				.startTime(1)
				.endTime(2)
				.onDate(new Date())
				.candidate(new ApplicationUser(1L, "Saeed", "CANDIDATE"))
				.interviewers(Arrays.asList(
						new ApplicationUser(2L, "Ahmed", "INTERVIEWER"),
						new ApplicationUser(3L, "Ali", "INTERVIEWER")))
				.build()
				
				);
		
		when(service.getAvaialbleTimeSlots(request)).thenReturn(availableTimeSlots);
		assertEquals(1, service.getAvaialbleTimeSlots(request).size());
	}

	@Test
	void testAddTimeSlot() {
		UserTimeSlot slot =	 UserTimeSlot.builder()
				.day("THURSDAY")
				.onDate(new Date())
				.startTime(9)
				.endTime(10)
				.user(new ApplicationUser(1L, "Saeed", "INTERVIEWER"))
				.build();
		when(repository.save(slot)).thenReturn(slot);
        assertEquals(slot, repository.save(slot));
	}


}
