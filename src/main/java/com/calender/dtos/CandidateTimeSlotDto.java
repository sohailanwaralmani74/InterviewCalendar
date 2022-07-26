package com.calender.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
@Data
public class CandidateTimeSlotDto {
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
	@JsonProperty("candidate")
	@NotNull
	private CandidateDto candidate;
	@JsonProperty("booked")
	private boolean booked;
	@JsonProperty("active")
	private boolean active;
	
}
