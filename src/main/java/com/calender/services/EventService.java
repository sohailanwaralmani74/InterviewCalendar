package com.calender.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.entities.Event;
import com.calender.repositories.EventRepository;

@Service
public class EventService  {

	@Autowired
	private EventRepository eventRepository;
	
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	
	public List<Event> getAllEventsForUser(long id) {
		return eventRepository.findAllByInterviewerId(id);
	}
	
	public Event addEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public void deleteEvent(long id) {
		eventRepository.deleteById(id);

	}

	public Event getEvent(long id) {
		return eventRepository.findById(id);

	}
	
	public List<Event> addEvent(List<Event> events) {
		return eventRepository.saveAll(events);
	}

}
