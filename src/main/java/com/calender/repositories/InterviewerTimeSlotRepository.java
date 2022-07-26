package com.calender.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calender.entities.InterviewerTimeSlot;

@Repository
public interface InterviewerTimeSlotRepository extends JpaRepository<InterviewerTimeSlot, Long> {
         
	InterviewerTimeSlot findById(long id);
	@Query("SELECT u FROM InterviewerTimeSlot u WHERE u.interviewer.id = ?1")
	List<InterviewerTimeSlot> findAllByUserId(long id);
	@Query("SELECT u FROM InterviewerTimeSlot u WHERE u.active = true AND u.booked = false")
	List<InterviewerTimeSlot> findAll();
	@Query("SELECT u FROM InterviewerTimeSlot u WHERE u.fromTime = ?1 AND u.onDate = ?2")
	InterviewerTimeSlot findByFromTimeAndOnDate(LocalTime fromTime, LocalDate onDate);
}
