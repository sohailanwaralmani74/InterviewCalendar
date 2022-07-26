package com.calender.validators;

import java.util.List;

import com.calender.dtos.EventDto;

public class EventValidator {
	
	public static boolean validateEvents(EventDto event) {
		
		if(event.getInterviewer().getFromTime()!= event.getCandidate().getFromTime())
			return true;
		
		return false;
		
	}

	public static EventDto validateDuplicateEvents(List<EventDto> eventsList) {
		for (int i = 0; i < eventsList.size(); i++) {
			for (int a = 0; a < eventsList.size(); a++) {
				if (eventsList.get(i).getCandidate().getFromTime() == eventsList.get(a).getCandidate().getFromTime() 
						&& i!=a && eventsList.get(i).getCandidate().getOnDate().isEqual(eventsList.get(a).getCandidate().getOnDate())
						|| eventsList.get(i).getInterviewer().getFromTime() == eventsList.get(a).getInterviewer().getFromTime()
						&& eventsList.get(i).getInterviewer().getOnDate().isEqual(eventsList.get(a).getInterviewer().getOnDate())) {

					return eventsList.get(i);
				}
			}
		}	
		return null;
	}
}
