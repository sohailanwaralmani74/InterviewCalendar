package com.calender.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
public class InterviewerTimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalTime fromTime;
	private LocalTime toTime;
	private LocalDate onDate;
	@ManyToOne
	private Interviewer interviewer;
	private boolean booked;
	private boolean active;

}
