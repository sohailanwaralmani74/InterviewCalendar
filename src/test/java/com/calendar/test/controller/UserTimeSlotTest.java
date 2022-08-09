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

import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.UserDto;
import com.calender.dtos.UserTimeSlotDto;
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
	void testGetAllTimeSlots() {
		List<UserTimeSlot> userList = Arrays.asList(
				new UserTimeSlot(1L, "01:00:00","02:00:00",new Date(), new ApplicationUser(1L, "Saeed", "INTERVIEWER")));
		when(repository.findAll()).thenReturn(userList);
		assertEquals(1, repository.findAll().size());
	}

	@Test
	void testGetAvailableSlots() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAddTimeSlot() {
		UserTimeSlot slot =	new UserTimeSlot(1L, "01:00:00","02:00:00",new Date(), new ApplicationUser(1L, "Saeed", "INTERVIEWER"));
		when(repository.save(slot)).thenReturn(slot);
        assertEquals(slot, repository.save(slot));
	}

	@Test
	void testUpdateTimeSlot() {
	UserTimeSlotDto slot =	new UserTimeSlotDto(1L, "03:00:00","04:00:00",new Date(), new UserDto(1L, "Saeed", "INTERVIEWER"));

	CustomAPIResponse response  = (CustomAPIResponse) when(service.updateTimeSlot(slot, 1l)).thenReturn(new CustomAPIResponse("200","SUCCESS","User has updated"));
	assertNotNull(response); 
    assertEquals(response, new CustomAPIResponse("200","SUCCESS","User has updated")); 
	}

	@Test
	void testDeleteTimeSlot() {
		CustomAPIResponse customResponse = new CustomAPIResponse("200","SUCCESS","User has been deleted");
		CustomAPIResponse response  = (CustomAPIResponse) when(service.deleteTimeSlot(1L)).thenReturn(new CustomAPIResponse("200","SUCCESS","User has been deleted"));
		assertNotNull(response); 
	    assertEquals(response, customResponse); 
	}

}
