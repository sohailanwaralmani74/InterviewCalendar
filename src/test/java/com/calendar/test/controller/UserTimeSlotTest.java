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
				.build());
		when(service.getAvaialbleTimeSlots(1,Arrays.asList("Ahmed","Ali"))).thenReturn(availableTimeSlots);
		assertEquals(1, service.getAvaialbleTimeSlots(1,Arrays.asList("Ahmed","Ali")).size());
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
