package com.calender.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calender.dtos.CustomAPIResponse;
import com.calender.dtos.UserDto;
import com.calender.entities.ApplicationUser;
import com.calender.exceptions.BusinessException;
import com.calender.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;

	public List<UserDto> getAllUsers() {
		List<UserDto> usersList = new ArrayList<>();
		try {
		List<ApplicationUser> applicationUsersList = userRepository.findAll();
		 usersList = applicationUsersList.stream()
		        .map(user -> new UserDto(user.getId(), user.getName(),user.getRole()))
		        .collect(Collectors.toList());
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("au-0001","FAILURE", "Error while Fetching user");
		}
		return usersList;

	}

	public CustomAPIResponse addUser(UserDto userDto) {
		CustomAPIResponse apiResponse =null;
		try {
			ApplicationUser user = mapper.map(userDto, ApplicationUser.class);
			userRepository.save(user);
			apiResponse = new CustomAPIResponse("200","SUCCESS","User Saved Successfully");
		} catch (Exception e) {
			throw new BusinessException("au-002","FAILURE", "Error while saving user");
		}

		return apiResponse;
	}

	public CustomAPIResponse updateUser(UserDto userDto, long id) {
		CustomAPIResponse apiResponse = null;
		try {
			ApplicationUser user = mapper.map(userDto, ApplicationUser.class);
			user.setId(id);
			userRepository.save(user);
			apiResponse = new CustomAPIResponse("200","SUCCESS","User update Successfully");
		} catch (Exception e) {
			throw new BusinessException("au-003","FAILURE", "Error while updating user");
		}
		return apiResponse;
	}

	public CustomAPIResponse deleteUser(long id) {
		CustomAPIResponse apiResponse = null;
		try {
			userRepository.deleteById(id);
			apiResponse = new CustomAPIResponse("200","SUCCESS","User Deleted Successfully");
		} catch (Exception e) {
			throw new BusinessException("au-004","FAILURE", "Error while Deleting user");
		}
		return apiResponse;
	}

}
