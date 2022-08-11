package com.calender.dtos;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("startTime")
	@Min(value = 9)
	@Max(value = 16)
	private int startTime;

	@JsonProperty("endTime")
	@Min(value = 10)
	@Max(value = 16)
	private int endTime;

	@JsonProperty("onDate")
	@NotNull
	private List<WeekDays> weekDays;
}
