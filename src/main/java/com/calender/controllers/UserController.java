package com.calender.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.UserDto;
import com.calender.services.UserService;

/**
 * @author sohail anwar
 * 
 *         UserController
 * 
 *         UserController is handler for CRUD request for User and handle below
 *         request.
 * 
 *         find all Users including interviewers and candidates, add user update
 *         user delete user
 * 
 * 
 * 
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> getAllusers(@Pattern(regexp = "INTERVIEWER|CANDIDATE") @RequestParam String role,
			@Positive @RequestParam(defaultValue = "0") Integer pageNo,
			@Max(value = 10) @Positive @RequestParam(defaultValue = "10") Integer pageSize) {
		return new ResponseEntity<>(userService.getAllUsers(role, pageNo,pageSize), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> adduser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateuser(@Valid @RequestBody UserDto userDto, @PathVariable long id) {
		return new ResponseEntity<>(userService.updateUser(userDto, id), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
