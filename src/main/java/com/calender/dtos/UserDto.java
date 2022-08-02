package com.calender.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.calender.validators.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)

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

	public UserDto() {
		super();
	}

	public UserDto(Long id,
			@NotEmpty @Size(min = 3, message = "user name should have at least 3 characters") String name,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
