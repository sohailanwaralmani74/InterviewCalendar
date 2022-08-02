package com.calender.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calender.entities.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
	@Query(value = "SELECT * FROM APPLICATION_USER", nativeQuery = true)
	public List<ApplicationUser> getAllUsers();

}
