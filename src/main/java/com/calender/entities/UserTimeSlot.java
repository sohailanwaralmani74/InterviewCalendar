package com.calender.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTimeSlot {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.springframework.data.annotation.Id
	private Long id;
	@Column(name = "from_time")
	private String fromTime;
	@Column(name = "to_time")
	private String toTime;
	private Date onDate;
	@ManyToOne
	private ApplicationUser user;

}
