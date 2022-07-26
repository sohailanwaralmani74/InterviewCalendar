package com.calender.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calender.entities.CandidateTimeSlot;

@Repository
public interface CandidateTimeSlotRepository extends JpaRepository<CandidateTimeSlot, Long> {
         
	CandidateTimeSlot findById(long id);
	@Query("SELECT u FROM CandidateTimeSlot u WHERE u.candidate.id = ?1")
	List<CandidateTimeSlot> findAllByUserId(long id);
	@Query("SELECT u FROM CandidateTimeSlot u WHERE u.active = true AND u.booked = false")
	List<CandidateTimeSlot> findAll();
	@Query("SELECT u FROM CandidateTimeSlot u WHERE u.fromTime = ?1 AND u.onDate = ?2")
	CandidateTimeSlot findByFromTimeAndOnDate(LocalTime fromTime, LocalDate onDate);
}
