package com.tutorial.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrSignInDto {
	
	@NotNull
	private String email;
	@NotNull
	@Email
	private String password;
	

}
