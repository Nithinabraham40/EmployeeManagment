package com.tutorial.employee.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_authEmployee")
public class AuthenticationEmployee {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	private LocalDate creationDate;
	@OneToOne
	@JoinColumn(name = "fk_empid")
	private Employee employee;
	public AuthenticationEmployee(Employee employee) {
		super();
		this.employee = employee;
		this.creationDate=LocalDate.now();
		this.token=UUID.randomUUID().toString();
		
	}
	
	
	
	
	
}
