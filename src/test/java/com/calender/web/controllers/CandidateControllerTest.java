package com.calender.web.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.calender.controllers.UserController;
import com.calender.repositories.UserRepository;
import com.calender.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class CandidateControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	@InjectMocks
    UserController candidateController;
    @MockBean
    UserService candidateService;
    @Autowired
    private ObjectMapper objectMapper;

	@Test
	void testGetAllCandidates() {
		
	}

	@Test
	void testGetCandidate() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCandidateCandidateDto() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCandidateListOfCandidateDto() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCandidate() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteCandidate() {
		fail("Not yet implemented");
	}

}
