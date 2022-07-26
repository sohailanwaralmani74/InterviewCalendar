package com.calender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calender.entities.Interviewer;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {

	public Interviewer findById(long id);
}
