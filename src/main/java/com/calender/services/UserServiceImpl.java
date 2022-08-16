package com.calender.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.calender.dtos.UserDto;
import com.calender.entities.ApplicationUser;
import com.calender.exceptions.BusinessException;
import com.calender.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private ModelMapper mapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;	
	}
	public List<UserDto> getAllUsers(String role, Integer pageNo, Integer pageSize) {
		List<UserDto> usersList = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			Page<ApplicationUser> pagedResult = userRepository.findAllByRole(role, pageable);
			
			usersList = pagedResult.getContent().stream()
					.map(user -> new UserDto(user.getId(), user.getName(), user.getRole()))
					.collect(Collectors.toList());		
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("au-0001", "Error while Fetching user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return usersList;
	}
	public ApplicationUser addUser(UserDto userDto) {
		try {
			ApplicationUser user = mapper.map(userDto, ApplicationUser.class);
			user = userRepository.save(user);
			return user;	
		} catch (Exception e) {
			throw new BusinessException("au-001", "Error while saving user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ApplicationUser updateUser(UserDto userDto, long id) {
		try {	
			ApplicationUser user = mapper.map(userDto, ApplicationUser.class);
			user.setId(id);
			user = userRepository.save(user);
			return user;	
		} catch (Exception e) {
			throw new BusinessException("au-003", "User does not exists with id:" + id, HttpStatus.NOT_FOUND);
		}
	}
	public void deleteUser(long id) {
		try {
			userRepository.deleteById(id);	
		} catch (Exception e) {
			throw new BusinessException("au-003", "User does not exists with id:" + id, HttpStatus.NOT_FOUND);
		}
	}
	public UserDto findById(long id) {
		try {
			Optional<ApplicationUser> user = userRepository.findById(id);
			UserDto userDto = mapper.map(user.get(), UserDto.class);
			return userDto;
		} catch (Exception e) {
			throw new BusinessException("au-003", "User does not exists with id:" + id, HttpStatus.NOT_FOUND);
		}
	}
}
