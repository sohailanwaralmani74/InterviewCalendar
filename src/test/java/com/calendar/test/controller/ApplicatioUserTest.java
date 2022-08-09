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

import com.calender.dtos.CustomAPIResponse;
import com.calender.entities.ApplicationUser;
import com.calender.repositories.UserRepository;
import com.calender.services.UserService;

@RunWith(MockitoJUnitRunner.class)
class ApplicatioUserTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;
	
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

	@Test
	void testUpdateuser() {
		ApplicationUser applicationUser =  new ApplicationUser(1L, "Saeed", "INTERVIEWER");
		ApplicationUser updated = userRepository.save(applicationUser); 
		    assertNotNull(updated); 
		    assertEquals(updated, applicationUser); 
	}

	/**
	 * delete API test
	 * 
	 * delete api is tested with service class instead of calling repository method directly so we check our custom
	 * response in our test cases.
	 * if we need to test delete method using repo below code can be replaced with,
	 * 
	 * Mockito.when(userRepositry.deleteById(1L)).thenReturn("SUCCESS");
	 * 
	 * as deleteById is a void method so we cannot asign results directly to an object.
	 * */
	@Test
	void testDeleteuser() {
		CustomAPIResponse customResponse = new CustomAPIResponse("200","SUCCESS","User has been deleted");
		CustomAPIResponse response  = (CustomAPIResponse) when(userService.deleteUser(1L)).thenReturn(new CustomAPIResponse("200","SUCCESS","User has been deleted"));
		assertNotNull(response); 
	    assertEquals(response, customResponse); 
	
	}

}
