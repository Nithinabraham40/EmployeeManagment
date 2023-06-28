package com.tutorial.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_record")
public class EmployeeRecord {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String attendence;
	private String performance;
	private String projectsWorkingOn;
	private String keySkills;
	private boolean isInNoticePeroiod;
	private String education;
	private String discreption;
	
	@OneToOne
	@JoinColumn(name = "fk_emp_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "fk_hr_id")
	private Hr hr;
	
	

	
	
	
}
