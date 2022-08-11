package com.calender.services;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.dtos.AvailableSlotsRequest;
import com.calender.dtos.AvailableTimeSlot;
import com.calender.dtos.UserTimeSlotDto;
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
public class UserTimeSlotServiceImpl {
	@Autowired
	private UserTimeSlotRepository repository;
	@Autowired
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

	public List<AvailableTimeSlot> getAvaialbleTimeSlots(AvailableSlotsRequest request) {
		try {

			UserTimeSlot candidateTimeSlot = repository.findByUserId(request.getCandidateId());
			List<UserTimeSlot> interviewerTimeSlot = repository.findAllByUserIdIn(request.getInterviewersIds());
			if (candidateTimeSlot == null || interviewerTimeSlot == null)
				return new ArrayList<>();

			return composeAvailableSlotsList(candidateTimeSlot, interviewerTimeSlot);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-001", "FAILURE", "Error while fetching Slot");
		}
	}

	public List<UserTimeSlot> addTimeSlot(List<UserTimeSlotDto> userTimeSlot) {
		try {
			List<UserTimeSlot> timeSlotList = prepareSlotsForPersistance(userTimeSlot);
			return repository.saveAll(timeSlotList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ts-003", "FAILURE", "Error while Adding Slot");
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
	private List<AvailableTimeSlot> composeAvailableSlotsList(UserTimeSlot slot,
			List<UserTimeSlot> interviewerTimeSlot) {

		List<ApplicationUser> interviewers = new ArrayList<>();
		List<AvailableTimeSlot> availableTimeSlots = new ArrayList<>();

		AvailableTimeSlot availableTimeSlot = AvailableTimeSlot.builder()
				.fromTime(slot.getStartTime())
				.toTime(slot.getEndTime())
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
	 *  private method
	 * 
	 * @param UserTimeSlot
	 * 
	 *  set list of time slots in given time.
	 *  @return slots
	 * 
	 */

	private List<UserTimeSlot> prepareSlotsForPersistance(List<UserTimeSlotDto> userTimeSlotDtoList) {
		List<UserTimeSlot> slots = new ArrayList<>();

		for (UserTimeSlotDto timeSlotDto : userTimeSlotDtoList) {
			int totalSlots = timeSlotDto.getEndTime() - timeSlotDto.getStartTime();
			for (int j = 0; j < timeSlotDto.getWeekDays().size(); j++) {
				for (int i = 0; i < totalSlots; i++) {
					UserTimeSlot timeSlot = UserTimeSlot.builder()
							.startTime(timeSlotDto.getStartTime() + i)
							.endTime(timeSlotDto.getStartTime() + i + 1)
							.onDate(timeSlotDto.getWeekDays().get(j).getOndate())
							.day(timeSlotDto.getWeekDays().get(j).getDay())
							.user(mapper.map(timeSlotDto.getUser(), ApplicationUser.class))
							.build();
					slots.add(timeSlot);
				}
			}

		}

		return slots;
	}
}
