package com.calender.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableSlotsRequest {

	@NotNull(message = "Please provide valid candidate Id")
	private Long candidateId;
	
	@NotNull(message = "Please provide valid Interviewer Ids")
	private List<Long> interviewersIds;

}
