package com.calender.utils;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import com.calender.entities.InterviewerTimeSlot;

public class InterviewerUtils {


	public static List<InterviewerTimeSlot> getSlots(List<InterviewerTimeSlot> timeSlotList) {
		List<InterviewerTimeSlot> responseList = new ArrayList<>();
		for(InterviewerTimeSlot slot:timeSlotList) {
			long hours = slot.getFromTime().until(slot.getToTime(), ChronoUnit.HOURS);
			for(int a=0; a<hours; a++) {
				InterviewerTimeSlot timeSlot =new InterviewerTimeSlot();
				timeSlot.setActive(true);
				timeSlot.setFromTime(slot.getFromTime().plusHours(a));
				timeSlot.setToTime(slot.getFromTime().plusHours(a+1));
				timeSlot.setOnDate(slot.getOnDate());
				timeSlot.setBooked(true);
				timeSlot.setInterviewer(slot.getInterviewer());
				responseList.add(timeSlot);
			}
		}
		return responseList;
	}
	
	public static List<InterviewerTimeSlot> getSlots(InterviewerTimeSlot slot) {
		List<InterviewerTimeSlot> responseList = new ArrayList<>();
		
			long hours = slot.getFromTime().until(slot.getToTime(), ChronoUnit.HOURS);
			for(int a=0; a<hours; a++) {
				InterviewerTimeSlot timeSlot =new InterviewerTimeSlot();
				timeSlot.setActive(true);
				timeSlot.setFromTime(slot.getFromTime().plusHours(a));
				timeSlot.setToTime(slot.getFromTime().plusHours(a+1));
				timeSlot.setOnDate(slot.getOnDate());
				timeSlot.setBooked(true);
				timeSlot.setInterviewer(slot.getInterviewer());
				responseList.add(timeSlot);

		}
		return responseList;
	}
	
	public static List<InterviewerTimeSlot> removeExistingSlotsFromList(List<InterviewerTimeSlot> timeSlotList, List<InterviewerTimeSlot> existingtimeSlotList){
		boolean b = false;
		List<InterviewerTimeSlot> newtimeSlotList = new ArrayList<>();
		for(InterviewerTimeSlot slot1 :timeSlotList) {
			for(InterviewerTimeSlot slot2 :existingtimeSlotList) {
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
