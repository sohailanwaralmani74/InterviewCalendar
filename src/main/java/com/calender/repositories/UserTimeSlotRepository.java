package com.calender.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calender.entities.UserTimeSlot;

@Repository
public interface UserTimeSlotRepository extends JpaRepository<UserTimeSlot, Long> {

	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from User_Time_Slot ts where ts.on_date = :onDate and ts.from_time = :fromTime and ts.user_id_id in(:interviewers)"
	 * ) public List<UserTimeSlot> getAvailableSlots(@Param("interviewers")
	 * List<Long> interviewers,
	 * 
	 * @Param("fromTime") String fromTime, @Param("onDate") LocalDate onDate);
	 */
	public UserTimeSlot findByUserId(long candidateId);
	public List<UserTimeSlot> findAllByUserIdIn( List<Long> interviewersIds);
	
}
