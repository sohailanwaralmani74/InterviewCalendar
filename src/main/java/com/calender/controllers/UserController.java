package com.calender.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.UserDto;
import com.calender.services.UserService;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserDto> getAllusers() {
		return userService.getAllUsers();	
	}
	@PostMapping
	public CustomAPIResponse adduser(@Valid @RequestBody UserDto userDto) {

		
		return userService.addUser(userDto);
		
	}
	@PutMapping("/{id}")
	public CustomAPIResponse updateuser(@Valid @RequestBody UserDto userDto, @PathVariable long id) {

		return userService.updateUser(userDto, id);

	}
	
	@DeleteMapping("/{id}")
	public CustomAPIResponse deleteuser(@PathVariable long id) {
		
			return userService.deleteUser(id);
	}

}
