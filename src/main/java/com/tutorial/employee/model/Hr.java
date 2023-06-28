package com.tutorial.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_hr")
public class Hr {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hr_id;
	@NotNull
	private String name;
	@NotNull
	@Email
	private String email;
	@NotNull
	private int experience;
	@NotNull
	private String password;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Department depManaged;
	
	
	

}
