package com.calender.services;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.dtos.AvailableSlotsRequest;
import com.calender.dtos.AvailableTimeSlot;
import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.UserDto;
import com.calender.dtos.UserTimeSlotDto;
import com.calender.entities.ApplicationUser;
import com.calender.entities.UserTimeSlot;
import com.calender.exceptions.BusinessException;
import com.calender.repositories.UserTimeSlotRepository;

@Service
public class UserTimeSlotService {
	@Autowired
	private UserTimeSlotRepository repository;
	@Autowired
	private ModelMapper mapper;

	public List<AvailableTimeSlot> getAvaialbleTimeSlots(@Valid AvailableSlotsRequest request) {
		List<AvailableTimeSlot> availableTimeSlots = new ArrayList<>();
		try {
			
			List<UserTimeSlot> candidateTimeSlot = repository.findAllByUserId(request.getCandidateId());
			
			for(UserTimeSlot slot:candidateTimeSlot) {
				List<UserTimeSlot> userTimeSlots = repository.getAvailableSlots(request.getInterviewersIds(),slot.getFromTime(),slot.getOnDate());
				availableTimeSlots.add(fillAvailableSlots(slot, userTimeSlots));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-001","FAILURE", "Error while fetching Slot");
		}
		return availableTimeSlots;
	}
	
	public List<UserTimeSlotDto> findAll() {
		List<UserTimeSlotDto> responseList = new ArrayList<>();
		try {
			List<UserTimeSlot> userTimeSlots = repository.findAll();
			responseList = userTimeSlots.stream()
			        .map(slot -> new UserTimeSlotDto(slot.getId(), slot.getFromTime(),slot.getToTime(),slot.getOnDate(),mapper.map(slot.getUserId(),UserDto.class)))
			        .collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-002","FAILURE", "Error while Fetching Slot");
		}
		return responseList;
	}

	public CustomAPIResponse addTimeSlot(UserTimeSlotDto timeSlot) {

		CustomAPIResponse apiResponse = null;
		try {
			LocalTime fromTime =LocalTime.parse(timeSlot.getFromTime());
			LocalTime toTime =LocalTime.parse(timeSlot.getToTime());
			if (fromTime.until(toTime, ChronoUnit.HOURS) >1) {
				throw new BusinessException("ts-005","FAILURE", "Error while Adding Slot");
			}
			
			UserTimeSlot slot = mapper.map(timeSlot, UserTimeSlot.class);
			repository.save(slot);
			apiResponse = new CustomAPIResponse("200", "SUCCESS", "Slot Added Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-003","FAILURE", "Error while Adding Slot");
		}
		return apiResponse;
	}

	public CustomAPIResponse updateTimeSlot(UserTimeSlotDto timeSlot, long id) {

		CustomAPIResponse apiResponse = null;
		try {
			UserTimeSlot slot = mapper.map(timeSlot, UserTimeSlot.class);
			slot.setId(id);
			repository.save(slot);
			apiResponse = new CustomAPIResponse("200", "SUCCESS", "User update Successfully");
		} catch (Exception e) {
			throw new BusinessException("ts-003","FAILURE", "Error while updating user");
		}
		return apiResponse;
	}

	public CustomAPIResponse deleteTimeSlot(long id) {
		CustomAPIResponse apiResponse = null;
		try {
			repository.deleteById(id);
			apiResponse = new CustomAPIResponse("200", "SUCCESS", "Slot Deleted Successfully");
		} catch (Exception e) {
			throw new BusinessException("ts-004","FAILURE", "Error while Deleting Slot");
		}
		return apiResponse;
	}
	
	private AvailableTimeSlot fillAvailableSlots(UserTimeSlot slot,List<UserTimeSlot> userTimeSlots) {
		AvailableTimeSlot availableTimeSlot = new AvailableTimeSlot();
		availableTimeSlot.setCandidate(slot.getUserId());
		availableTimeSlot.setFromTime(slot.getFromTime());
		availableTimeSlot.setToTime(slot.getToTime());
		availableTimeSlot.setOnDate(slot.getOnDate());
		List<ApplicationUser> applicationUsers =new ArrayList<>();
		for(UserTimeSlot userTimeSlot:userTimeSlots) 
			applicationUsers.add(userTimeSlot.getUserId());
		availableTimeSlot.setInterviewers(applicationUsers);
		return availableTimeSlot;
	}

}
