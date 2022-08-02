package com.calender.dtos;

import java.time.LocalDate;
import java.util.List;

import com.calender.entities.ApplicationUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableTimeSlot {

	private String fromTime;
	private String toTime;
	private LocalDate onDate;
	private ApplicationUser candidate;
	private List<ApplicationUser> interviewers;


	

}
