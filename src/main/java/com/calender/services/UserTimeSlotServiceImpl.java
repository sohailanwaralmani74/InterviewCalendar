package com.calender.services;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.calender.dtos.AvailableTimeSlot;
import com.calender.dtos.TimeSlotDTO;
import com.calender.dtos.TimeSlotRequestDto;
import com.calender.entities.ApplicationUser;
import com.calender.entities.UserTimeSlot;
import com.calender.exceptions.BusinessException;
import com.calender.repositories.UserTimeSlotRepository;

/**
 * @author sohail anwar
 * 
 *         UserTimeSlotService UserTimeSlotService handles request from
 *         UserTimeSlotController
 * 
 *         Using @ ModelMapper to map DTO with entities using @
 *         UserTimeSlotRepository
 * 
 */

@Service
public class UserTimeSlotServiceImpl implements UserTimeSlotService{

	private UserTimeSlotRepository repository;
	private ModelMapper mapper;

	/**
	 * @Method getAvaialbleTimeSlots,
	 * 
	 *         First we fetch time slots for candidate with particular Id. then we
	 *         fetch Interviewers against Ids list. passing lists to method
	 *         fillAvailableSlots for further processing
	 * 
	 * @return availableTimeSlots
	 * @throws BusinessException
	 * 
	 */
	
	@Autowired
	public UserTimeSlotServiceImpl(UserTimeSlotRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	public List<AvailableTimeSlot> getAvaialbleTimeSlots(long candidateId, List<String> interviewers) {
		try {
			UserTimeSlot candidateTimeSlot = repository.findByUserId(candidateId);
			List<UserTimeSlot> interviewerTimeSlot = repository.findAllByUserNameIn(interviewers);
			if (candidateTimeSlot == null || interviewerTimeSlot == null)
				return new ArrayList<>();
			return composeAvailableSlotsList(candidateTimeSlot, interviewerTimeSlot);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-001", "Error while fetching Slot", HttpStatus.NO_CONTENT);
		}
	}
	public List<UserTimeSlot> addTimeSlot(TimeSlotRequestDto userTimeSlot) {
		try {
			List<UserTimeSlot> timeSlotList = prepareSlotsForPersistance(userTimeSlot);
			return repository.saveAll(timeSlotList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-003", "Error while Adding Slot", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Method composeAvailableSlotsList
	 * 
	 *         private method
	 * 
	 *         get below parameters as input
	 * 
	 *         availableTimeSlots (an empty list) UserTimeSlot time slot object for
	 *         candidate interviewerTimeSlot list for interviewers time slots
	 * 
	 *         get interview start time and on date from candidate slot object and
	 *         will compare if any is available in interviewers and add to to
	 *         available time slot otherwise will ignore it.
	 * 
	 * @return availableTimeSlots
	 * 
	 */
	private List<AvailableTimeSlot> composeAvailableSlotsList(UserTimeSlot slot,List<UserTimeSlot> interviewerTimeSlot) {
		List<ApplicationUser> interviewers = new ArrayList<>();
		List<AvailableTimeSlot> availableTimeSlots = new ArrayList<>();
		AvailableTimeSlot availableTimeSlot = AvailableTimeSlot.builder()
				.startTime(slot.getStartTime())
				.endTime(slot.getEndTime())
				.onDate(slot.getOnDate())
				.day(slot.getDay())
				.candidate(slot.getUser())
				.build();
		for (UserTimeSlot slot2 : interviewerTimeSlot) {
			if (slot.getStartTime() == slot2.getStartTime() && slot.getOnDate() == slot2.getOnDate()) {
				interviewers.add(slot2.getUser());
			}
		}
		availableTimeSlot.setInterviewers(interviewers);
		availableTimeSlots.add(availableTimeSlot);
		return availableTimeSlots;
	}

	/**
	 * @Method prepareSlotsForPersistance
	 * 
	 * @param id 
	 * @param UserTimeSlot
	 *  set list of time slots in given time.
	 *  
	 *  @return slots
	 * 
	 */

	private List<UserTimeSlot> prepareSlotsForPersistance(TimeSlotRequestDto timeSlotRequestDto) {
		List<UserTimeSlot> slots = new ArrayList<>();
		for (TimeSlotDTO timeSlotDto : timeSlotRequestDto.getTimeSLots()) {
			int totalSlots = timeSlotDto.getEndTime() - timeSlotDto.getStartTime();
			for (int j = 0; j < timeSlotDto.getWeekDays().size(); j++) {
				for (int i = 0; i < totalSlots; i++) {
					UserTimeSlot timeSlot = UserTimeSlot.builder()
							.startTime(timeSlotDto.getStartTime() + i)
							.endTime(timeSlotDto.getStartTime() + i + 1)
							.onDate(timeSlotDto.getWeekDays().get(j).getOndate())
							.day(timeSlotDto.getWeekDays().get(j).getDay())
							.user(mapper.map(timeSlotRequestDto.getUser(), ApplicationUser.class))
							.build();
					slots.add(timeSlot);
				}
			}
		}
		return slots;
	}
}
