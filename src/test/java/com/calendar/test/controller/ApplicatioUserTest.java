package com.calendar.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.calender.entities.ApplicationUser;
import com.calender.repositories.UserRepository;
import com.calender.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class ApplicatioUserTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;
	
	public ApplicationUser getDto(){
		ApplicationUser userDto = new ApplicationUser(1L, "Saeed", "INTERVIEWER");

        return userDto;
    }

	@Test
	void testGetAllusers() {
		List<ApplicationUser> userList = Arrays.asList(
				new ApplicationUser(1L, "Saeed", "INTERVIEWER"),
				new ApplicationUser(2L, "Shanker", "CANDIDATE"), 
				new ApplicationUser(3L, "Sohail", "CANDIDATE"));

		when(userRepository.findAll()).thenReturn(userList);
		assertEquals(3, userRepository.findAll().size());
	}

	@Test
	void testAdduser() {
		ApplicationUser applicationUser =  new ApplicationUser(1L, "Saeed", "INTERVIEWER");
		when(userRepository.save(applicationUser)).thenReturn(applicationUser);
        assertEquals(getDto(), userRepository.save(applicationUser));
	}


}
