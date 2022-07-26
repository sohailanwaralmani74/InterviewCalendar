package com.calender.utils;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import com.calender.entities.CandidateTimeSlot;

public class CandidateUtils {


	public static List<CandidateTimeSlot> getSlots(List<CandidateTimeSlot> timeSlotList) {
		List<CandidateTimeSlot> responseList = new ArrayList<>();
		for(CandidateTimeSlot slot:timeSlotList) {
			long hours = slot.getFromTime().until(slot.getToTime(), ChronoUnit.HOURS);
			for(int a=0; a<hours; a++) {
				CandidateTimeSlot timeSlot =new CandidateTimeSlot();
				timeSlot.setActive(true);
				timeSlot.setFromTime(slot.getFromTime().plusHours(a));
				timeSlot.setToTime(slot.getFromTime().plusHours(a+1));
				timeSlot.setOnDate(slot.getOnDate());
				timeSlot.setBooked(true);
				timeSlot.setCandidate(slot.getCandidate());
				responseList.add(timeSlot);
			}
		}
		return responseList;
	}
	
	public static List<CandidateTimeSlot> getSlots(CandidateTimeSlot slot) {
		List<CandidateTimeSlot> responseList = new ArrayList<>();
		
			long hours = slot.getFromTime().until(slot.getToTime(), ChronoUnit.HOURS);
			for(int a=0; a<hours; a++) {
				CandidateTimeSlot timeSlot =new CandidateTimeSlot();
				timeSlot.setActive(true);
				timeSlot.setFromTime(slot.getFromTime().plusHours(a));
				timeSlot.setToTime(slot.getFromTime().plusHours(a+1));
				timeSlot.setOnDate(slot.getOnDate());
				timeSlot.setBooked(true);
				timeSlot.setCandidate(slot.getCandidate());
				responseList.add(timeSlot);

		}
		return responseList;
	}
	
	public static List<CandidateTimeSlot> removeExistingSlotsFromList(List<CandidateTimeSlot> timeSlotList, List<CandidateTimeSlot> existingtimeSlotList){
		boolean b = false;
		List<CandidateTimeSlot> newtimeSlotList = new ArrayList<>();
		for(CandidateTimeSlot slot1 :timeSlotList) {
			for(CandidateTimeSlot slot2 :existingtimeSlotList) {
				if(slot1.getFromTime() == slot2.getFromTime() && slot1.getOnDate() == slot2.getOnDate()) {
					b= true;
					break;
				}
				else
					b = false;
			}
			if(!b)
				newtimeSlotList.add(slot1);
		}
		
		return newtimeSlotList;
	}
}
