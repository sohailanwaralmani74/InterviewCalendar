package com.calender.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.calender.entities.Candidate;
import com.calender.entities.Interviewer;
import com.calender.repositories.CandidateRepository;
import com.calender.repositories.InterviewerRepository;

public class UserValidator {
	@Autowired
	private static CandidateRepository candidateRepository;
	@Autowired
	private static InterviewerRepository interviewerRepository;

	public static boolean doesCandidateExists(long id) {
		Candidate candidate = candidateRepository.findById(id);
		if (null != candidate)
			return true;
		return false;
	}

	public static boolean doesInterviewerExists(long id) {

		Interviewer interviewer = interviewerRepository.findById(id);

		if (null != interviewer)
			return true;
		return false;
	}
}
