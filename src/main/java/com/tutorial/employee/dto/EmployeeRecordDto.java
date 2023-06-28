package com.tutorial.employee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRecordDto {
	
	
	@NotNull
	private String attendence;
	@NotNull
	private String performance;
	@NotNull
	private String projectsWorkingOn;
	@NotNull
	private String keySkills;
	@NotNull
	private boolean isInNoticePeroiod;
	@NotNull
	private String education;
	@NotNull
	private String discription;
	@NotNull
	private Long emp_id;

}
