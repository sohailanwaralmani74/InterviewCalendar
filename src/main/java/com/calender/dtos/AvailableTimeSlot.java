package com.calender.dtos;

import java.util.Date;
import java.util.List;

import com.calender.entities.ApplicationUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableTimeSlot {

	private int startTime;
	private int endTime;
	private Date onDate;
	private String day;
	private ApplicationUser candidate;
	private List<ApplicationUser> interviewers;
}
