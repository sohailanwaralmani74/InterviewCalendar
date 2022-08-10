package com.calender.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_slot")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTimeSlot {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "from_time")
	private int fromTime;
	
	@Column(name = "to_time")
	private int toTime;
	
	@Column(name = "on_date")
	private Date onDate;
	@ManyToOne
	private ApplicationUser user;

}
