package com.calender.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.entities.InterviewerTimeSlot;
import com.calender.repositories.InterviewerTimeSlotRepository;
import com.calender.utils.InterviewerUtils;
@Service
public class InterviewerTimeSlotService {
	@Autowired
	private InterviewerTimeSlotRepository calenderRepository;

	public List<InterviewerTimeSlot> getAllTimeSlotsForUser(long userId) {

		return calenderRepository.findAllByUserId(userId);
	}

	public List<InterviewerTimeSlot> getAllTimeSlots() {

		return calenderRepository.findAll();
	}

	public InterviewerTimeSlot getTimeSlot(LocalTime fromTime, LocalDate onDate) {

		return calenderRepository.findByFromTimeAndOnDate(fromTime, onDate);
	}

	public InterviewerTimeSlot getTimeSlot(long id) {

		return calenderRepository.findById(id);
	}

	public List<InterviewerTimeSlot> addTimeSlot(InterviewerTimeSlot timeSlot) {
		List<InterviewerTimeSlot> savedList = calenderRepository.findAllByUserId(timeSlot.getInterviewer().getId());
		List<InterviewerTimeSlot> newTimeSlot = InterviewerUtils.getSlots(timeSlot);
		if (savedList != null && savedList.size() > 0)
			return calenderRepository.saveAll(InterviewerUtils.removeExistingSlotsFromList(newTimeSlot, savedList));
		else
			return calenderRepository.saveAll(newTimeSlot);
	}

	public List<InterviewerTimeSlot> addTimeSlot(List<InterviewerTimeSlot> timeSlot) {

		return calenderRepository.saveAll(InterviewerUtils.getSlots(timeSlot));
	}

	public void deleteTimeSlot(long id) {

		calenderRepository.deleteById(id);
	}

}
