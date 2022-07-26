package com.calender.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
@Data
public class EventDto {
	@JsonProperty("id")
	private Long id;
	private CandidateTimeSlotDto candidate;
	@JsonProperty("interviewer")
	private InterviewerTimeSlotDto interviewer;
	@JsonProperty("active")
	private boolean active = true;

}
