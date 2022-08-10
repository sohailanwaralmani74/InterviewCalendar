package com.calendar.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.calender.entities.ApplicationUser;
import com.calender.entities.UserTimeSlot;
import com.calender.repositories.UserTimeSlotRepository;
import com.calender.services.UserTimeSlotService;

@RunWith(MockitoJUnitRunner.class)
class UserTimeSlotTest {

	@Mock
	private UserTimeSlotRepository repository;
	
	@InjectMocks
	private UserTimeSlotService service;
	
	@Test
	void testGetAvailableSlots() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAddTimeSlot() {
		UserTimeSlot slot =	new UserTimeSlot(1L, 9,10,new Date(), new ApplicationUser(1L, "Saeed", "INTERVIEWER"));
		when(repository.save(slot)).thenReturn(slot);
        assertEquals(slot, repository.save(slot));
	}


}
