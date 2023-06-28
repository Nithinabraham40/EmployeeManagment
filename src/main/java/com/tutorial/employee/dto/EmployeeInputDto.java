package com.tutorial.employee.dto;

import com.tutorial.employee.model.Department;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInputDto {
	@NotNull
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	private int experience;
	@NotNull
	private int annualSalary;
	
	@NotNull
	private String password;

}
