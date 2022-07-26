package com.calender.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.entities.Candidate;
import com.calender.repositories.CandidateRepository;

@Service
public class CandidateService {
	@Autowired
	private CandidateRepository userRepository;

	public List<Candidate> getAllUsers() {
		return userRepository.findAll();
	}

	public Candidate addUser(Candidate user) {
		return userRepository.save(user);
	}

	public List<Candidate> addUser(List<Candidate> users) {
		return userRepository.saveAll(users);
	}

	public Candidate updateUser(Candidate user) {
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);

	}

	public Candidate getUser(long id) {

		return userRepository.findById(id);
	}
}
