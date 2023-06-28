package com.tutorial.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeOutputDto {
	
	
	private String name;
	private String email;
	private int experience;
	private int annualSalary;

}
