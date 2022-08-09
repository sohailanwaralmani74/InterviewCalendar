package com.calender.services;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

/**
 * @author sohail anwar
 * 
 * <blockquote> UserTimeSlotService
 * UserTimeSlotService handles request from UserTimeSlotController 
 * 
 * Using @ ModelMapper to map DTO with entities 
 * using @ UserTimeSlotRepository
 * 
 * */

@Service
public class UserTimeSlotService {
	@Autowired
	private UserTimeSlotRepository repository;
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * @Method getAvaialbleTimeSlots,
	 * 
	 * First we fetch time slots for candidate with particular Id.
	 * then we fetch Interviewers against Ids list.
	 * passing lists to method fillAvailableSlots for further processing
	 * 
	 * @return availableTimeSlots
	 * @throws BusinessException
	 * 
	 * */

	public List<AvailableTimeSlot> getAvaialbleTimeSlots(AvailableSlotsRequest request) {
		List<AvailableTimeSlot> availableTimeSlots = new ArrayList<>();
		try {

			UserTimeSlot candidateTimeSlot = repository.findByUserId(request.getCandidateId());
			List<UserTimeSlot> interviewerTimeSlot = repository.findAllByUserIdIn(request.getInterviewersIds());
			fillAvailableSlots(availableTimeSlots, candidateTimeSlot, interviewerTimeSlot);
            System.out.print("\n\n"+availableTimeSlots.size()+"\n\n");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-001", "FAILURE", "Error while fetching Slot");
		}
		return availableTimeSlots;
	}

	public List<UserTimeSlotDto> findAll() {
		List<UserTimeSlotDto> responseList = new ArrayList<>();
		try {
			List<UserTimeSlot> userTimeSlots = repository.findAll();
			responseList = userTimeSlots.stream().map(slot -> new UserTimeSlotDto(slot.getId(), slot.getFromTime(),
					slot.getToTime(), slot.getOnDate(), mapper.map(slot.getUser(), UserDto.class)))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-002", "FAILURE", "Error while Fetching Slot");
		}
		return responseList;
	}

	public CustomAPIResponse addTimeSlot(UserTimeSlotDto timeSlot) {

		CustomAPIResponse apiResponse = null;
		try {
			LocalTime fromTime = LocalTime.parse(timeSlot.getFromTime());
			LocalTime toTime = LocalTime.parse(timeSlot.getToTime());
			if (fromTime.until(toTime, ChronoUnit.HOURS) > 1) {
				throw new BusinessException("ts-005", "FAILURE", "From Time and to time should have 1 hour difference");
			}

			UserTimeSlot slot = mapper.map(timeSlot, UserTimeSlot.class);
			repository.save(slot);
			apiResponse = new CustomAPIResponse("200", "SUCCESS", "Slot Added Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-003", "FAILURE", "Error while Adding Slot");
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
			throw new BusinessException("ts-003", "FAILURE", "Error while updating user");
		}
		return apiResponse;
	}

	public CustomAPIResponse deleteTimeSlot(long id) {
		CustomAPIResponse apiResponse = null;
		try {
			repository.deleteById(id);
			apiResponse = new CustomAPIResponse("200", "SUCCESS", "Slot Deleted Successfully");
		} catch (Exception e) {
			throw new BusinessException("ts-004", "FAILURE", "Error while Deleting Slot");
		}
		return apiResponse;
	}

	/**
	 * @Method fillAvailableSlots 
	 * 
	 * private method 
	 * 
	 * get below parameters as input
	 * 
	 * availableTimeSlots (an empty list)
	 * UserTimeSlot time slot object for candidate
	 * interviewerTimeSlot list for interviewers time slots
	 * 
	 * get interview start time and on date from candidate slot object and will compare if 
	 * any is available in interviewers and add to to available time slot otherwise will ignore it.
	 * 
	 * @return availableTimeSlots
	 * 
	 * */
	private List<AvailableTimeSlot> fillAvailableSlots(List<AvailableTimeSlot> availableTimeSlots,
			UserTimeSlot slot, List<UserTimeSlot> interviewerTimeSlot) {

		
			AvailableTimeSlot availableTimeSlot = new AvailableTimeSlot();
			availableTimeSlot.setFromTime(slot.getFromTime());
			availableTimeSlot.setOnDate(slot.getOnDate());
			availableTimeSlot.setCandidate(slot.getUser());
			availableTimeSlot.setToTime(slot.getToTime());
			List<ApplicationUser> interviewers = new ArrayList<>();
			for(UserTimeSlot slot2:interviewerTimeSlot) {
				if(slot.getFromTime().equals(slot2.getFromTime()) && slot.getOnDate().equals(slot2.getOnDate())) {
					interviewers.add(slot2.getUser());
				}
				
			}
			availableTimeSlot.setInterviewers(interviewers);
			
			availableTimeSlots.add(availableTimeSlot);
		return availableTimeSlots;

	}
}
