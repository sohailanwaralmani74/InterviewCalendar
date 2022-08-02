package com.calender.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.calender.validators.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	@JsonProperty("id")
	private Long id;
	@NotEmpty
	@Size(min = 3, message = "user name should have at least 3 characters")
	@JsonProperty("name")
	private String name;
	@JsonProperty("role")
	@Role
	private String role;

}
