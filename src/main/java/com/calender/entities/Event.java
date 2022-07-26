package com.calender.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.calender.dtos.CandidateTimeSlotDto;
import lombok.Data;

@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private InterviewerTimeSlot interviewer;
	@ManyToOne
	private CandidateTimeSlotDto candidate;
	private boolean active;
}
