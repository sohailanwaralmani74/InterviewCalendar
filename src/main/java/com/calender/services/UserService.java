package com.calender.services;

import java.util.List;

import com.calender.dtos.UserDto;
import com.calender.entities.ApplicationUser;

public interface UserService {

	public List<UserDto> getAllUsers(String role, Integer pageNo, Integer pageSize);
	public ApplicationUser addUser(UserDto userDto);
	public ApplicationUser updateUser(UserDto userDto, long id);
	public void deleteUser(long id);
	public UserDto findById(long id);
}
