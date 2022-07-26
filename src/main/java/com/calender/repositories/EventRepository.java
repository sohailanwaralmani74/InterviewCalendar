package com.calender.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calender.entities.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findAllByUserId(long id);
	Event findById(long id);

}
