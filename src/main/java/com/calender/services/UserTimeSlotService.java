package com.calender.services;

import java.util.List;

import com.calender.dtos.AvailableSlotsRequest;
import com.calender.dtos.AvailableTimeSlot;
import com.calender.dtos.UserTimeSlotDto;
import com.calender.entities.UserTimeSlot;

public interface UserTimeSlotService {
	
	public List<AvailableTimeSlot> getAvaialbleTimeSlots(AvailableSlotsRequest request);
	public List<UserTimeSlot> addTimeSlot(List<UserTimeSlotDto> userTimeSlot);

}
