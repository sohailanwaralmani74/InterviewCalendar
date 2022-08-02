package com.calender.dtos;

import java.time.LocalDate;
import java.util.List;

import com.calender.entities.ApplicationUser;

public class AvailableTimeSlot {

	private String fromTime;
	private String toTime;
	private LocalDate onDate;
	private ApplicationUser candidate;
	
	private List<ApplicationUser> interviewers;

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public LocalDate getOnDate() {
		return onDate;
	}

	public void setOnDate(LocalDate onDate) {
		this.onDate = onDate;
	}

	public ApplicationUser getCandidate() {
		return candidate;
	}

	public void setCandidate(ApplicationUser candidate) {
		this.candidate = candidate;
	}

	public List<ApplicationUser> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(List<ApplicationUser> interviewers) {
		this.interviewers = interviewers;
	}

	

}
