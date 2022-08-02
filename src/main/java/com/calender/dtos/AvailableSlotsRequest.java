package com.calender.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

public class AvailableSlotsRequest {

	@NotNull(message = "Please provide valid candidate Id")
	private Long candidateId;
	@NotNull(message = "Please provide valid Interviewer Ids")
	private List<Long> interviewersIds;

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public List<Long> getInterviewersIds() {
		return interviewersIds;
	}

	public void setInterviewersIds(List<Long> interviewersIds) {
		this.interviewersIds = interviewersIds;
	}

}
