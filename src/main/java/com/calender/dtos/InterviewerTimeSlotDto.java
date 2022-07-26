package com.calender.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.calender.entities.Interviewer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
@Data
public class InterviewerTimeSlotDto {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("fromTime")
	@NotNull
	private LocalTime fromTime;
	@JsonProperty("toTime")
	@NotNull
	private LocalTime toTime;
	@JsonProperty("onDate")
	@NotNull
	private LocalDate onDate;
	@JsonProperty("interviewer")
	@NotNull
	private Interviewer interviewer;
	@JsonProperty("booked")
	private boolean booked;
	@JsonProperty("active")
	private boolean active;
	
}
