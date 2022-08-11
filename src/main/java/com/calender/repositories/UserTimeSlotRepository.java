package com.calender.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calender.entities.UserTimeSlot;

@Repository
public interface UserTimeSlotRepository extends JpaRepository<UserTimeSlot, Long> {

	/**
	 * findByUserId will create a join query and fetch data against user id provided
	 * 
	 * findAllByUserIdIn create outer join on application user table and will find with user names in (?,?,...)
	 * */	
	public UserTimeSlot findByUserId(long candidateId);
	public List<UserTimeSlot> findAllByUserNameIn(List<String> interviewers);
	
}
