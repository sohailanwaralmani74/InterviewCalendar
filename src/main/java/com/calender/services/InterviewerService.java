package com.calender.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.entities.Interviewer;
import com.calender.repositories.InterviewerRepository;

@Service
public class InterviewerService {
	@Autowired
	private InterviewerRepository userRepository;

	public List<Interviewer> getAllUsers() {
		return userRepository.findAll();
	}

	public Interviewer addUser(Interviewer user) {
		return userRepository.save(user);
	}

	public List<Interviewer> addUser(List<Interviewer> users) {
		return userRepository.saveAll(users);
	}

	public Interviewer updateUser(Interviewer user) {
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);

	}

	public Interviewer getUser(long id) {

		return userRepository.findById(id);
	}
}
