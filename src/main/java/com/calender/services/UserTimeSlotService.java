package com.calender.services;

import java.util.List;

import com.calender.dtos.AvailableTimeSlot;
import com.calender.dtos.TimeSlotRequestDto;
import com.calender.entities.UserTimeSlot;

public interface UserTimeSlotService {
	
	public List<AvailableTimeSlot> getAvaialbleTimeSlots(long candidateId, List<String> interviewers);
	public List<UserTimeSlot> addTimeSlot(TimeSlotRequestDto userTimeSlot);
}
