package com.calender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calender.entities.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	public Candidate findById(long id);

}
