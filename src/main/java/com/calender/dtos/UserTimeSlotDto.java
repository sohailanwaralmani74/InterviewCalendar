package com.calender.dtos;

import java.util.Date;

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
public class UserTimeSlotDto {
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("fromTime")
	@Min(value = 9)
	@Max(value = 16)
	private int fromTime;
	
	@JsonProperty("toTime")
	@Min(value = 10)
	@Max(value = 16)
	private int toTime;
	
	@JsonProperty("onDate")
	@NotNull
	private Date onDate;
	@JsonProperty("user")
	@NotNull
	private UserDto user;

}
