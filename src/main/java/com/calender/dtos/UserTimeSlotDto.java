package com.calender.dtos;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

import com.calender.validators.From2Time;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)

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
	private LocalDate onDate;
	@JsonProperty("user")
	@NotNull
	private UserDto userId;

	public UserTimeSlotDto() {
		super();
	}

	public UserTimeSlotDto(Long id, String fromTime, String toTime, @NotNull LocalDate onDate,
			@NotNull UserDto userId) {
		super();
		this.id = id;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.onDate = onDate;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public LocalDate getOnDate() {
		return onDate;
	}

	public void setOnDate(LocalDate onDate) {
		this.onDate = onDate;
	}

	public UserDto getUserId() {
		return userId;
	}

	public void setUserId(UserDto userId) {
		this.userId = userId;
	}

	

}
