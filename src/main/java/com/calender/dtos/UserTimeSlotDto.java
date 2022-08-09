package com.calender.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.calender.validators.From2Time;
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
public class UserTimeSlotDto {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("fromTime")
	@From2Time(message = "From time should be perfect hour eg '01:00:00'")
	private String fromTime;
	@JsonProperty("toTime")
	@From2Time(message = "From time should be perfect hour eg '01:00:00'")
	private String toTime;
	@JsonProperty("onDate")
	@NotNull
	private Date onDate;
	@JsonProperty("user")
	@NotNull
	private UserDto user;

}
