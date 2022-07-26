package com.calender.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calender.entities.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("SELECT u FROM Event u WHERE u.interviewer.id = ?1")
	List<Event> findAllByInterviewerId(long id);
	@Query("SELECT u FROM Event u WHERE u.candidate.id = ?1")
	List<Event> findAllByCandidateId(long id);
	Event findById(long id);

}
