package com.calender.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.EventDto;
import com.calender.entities.Event;
import com.calender.exceptions.SlotReservationException;
import com.calender.services.EventService;
import com.calender.validators.EventValidator;

@RestController("/events")
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<EventDto> getAllEvents() {
		List<EventDto> responseList = new ArrayList<EventDto>();
		List<Event> events = eventService.getAllEvents();
		mapper.map(responseList, events);
		return responseList;
	}

	@GetMapping("/{id}")
	public EventDto getEvent(@PathVariable long id) {

		Event event = eventService.getEvent(id);
		EventDto responseDto = mapper.map(event, EventDto.class);
		return responseDto;
	}

	@PostMapping
	public EventDto addEvent(@RequestBody EventDto eventDto) {
        
		if(EventValidator.validateEvents(eventDto))
			throw new SlotReservationException();
		
		Event event = mapper.map(eventDto, Event.class);
		Event serviceResponse = eventService.addEvent(event);
		EventDto responseDto = mapper.map(serviceResponse, EventDto.class);
		return responseDto;
	}
	
	@PostMapping("/all")
	public List<EventDto> addEvent(@RequestBody List<EventDto> eventsDto) {

		for(EventDto event:eventsDto) {
			if(EventValidator.validateEvents(event)) {
				throw new SlotReservationException();
				
			}
		}
		EventDto dto = EventValidator.validateDuplicateEvents(eventsDto);
		if(null != dto) {
			
		}
		List<EventDto> responseList = new ArrayList<EventDto>();
		List<Event> events = new ArrayList<Event>();
		mapper.map(events,eventsDto);
		List<Event> serviceResponseList = eventService.addEvent(events);
		mapper.map(serviceResponseList,responseList);
		return responseList;
	}

	@PutMapping
	public EventDto updateEvent(@RequestBody EventDto eventDto) {

		Event event = mapper.map(eventDto, Event.class);
		Event serviceResponse = eventService.addEvent(event);
		EventDto responseDto = mapper.map(serviceResponse, EventDto.class);
		return responseDto;
	}
	
	@DeleteMapping("/{id}")
	public CustomAPIResponse deleteEvent(@PathVariable long id) {
		CustomAPIResponse apiResponse = new CustomAPIResponse();
		try {
			eventService.deleteEvent(id);
			apiResponse.setCode("200");
			apiResponse.setStatus("SUCCESS");
			apiResponse.setMessage("Event with id:" + id + " Deleted Successfully");
		} catch (Exception e) {
			apiResponse.setCode("500");
			apiResponse.setStatus("FAILURE");
			apiResponse.setMessage("Error while deleting Event with id:" + id);
		}
		return apiResponse;
	}

}
