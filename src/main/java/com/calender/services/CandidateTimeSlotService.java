package com.calender.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.calender.entities.CandidateTimeSlot;
import com.calender.repositories.CandidateTimeSlotRepository;
import com.calender.utils.CandidateUtils;

public class CandidateTimeSlotService {
	@Autowired
	private CandidateTimeSlotRepository calenderRepository;

	public List<CandidateTimeSlot> getAllTimeSlotsForUser(long userId) {

		return calenderRepository.findAllByUserId(userId);
	}

	public List<CandidateTimeSlot> getAllTimeSlots() {

		return calenderRepository.findAll();
	}

	public CandidateTimeSlot getTimeSlot(LocalTime fromTime, LocalDate onDate) {

		return calenderRepository.findByFromTimeAndOnDate(fromTime, onDate);
	}

	public CandidateTimeSlot getTimeSlot(long id) {

		return calenderRepository.findById(id);
	}

	public List<CandidateTimeSlot> addTimeSlot(CandidateTimeSlot timeSlot) {
		List<CandidateTimeSlot> savedList = calenderRepository.findAllByUserId(timeSlot.getCandidate().getId());
		List<CandidateTimeSlot> newTimeSlot = CandidateUtils.getSlots(timeSlot);
		if (savedList != null && savedList.size() > 0)
			return calenderRepository.saveAll(CandidateUtils.removeExistingSlotsFromList(newTimeSlot, savedList));
		else
			return calenderRepository.saveAll(newTimeSlot);
	}

	public List<CandidateTimeSlot> addTimeSlot(List<CandidateTimeSlot> timeSlot) {

		return calenderRepository.saveAll(CandidateUtils.getSlots(timeSlot));
	}

	public void deleteTimeSlot(long id) {

		calenderRepository.deleteById(id);
	}

}
