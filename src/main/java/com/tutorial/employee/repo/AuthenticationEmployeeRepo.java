package com.tutorial.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.employee.model.AuthenticationEmployee;

public interface AuthenticationEmployeeRepo extends JpaRepository<AuthenticationEmployee, Long>{

	AuthenticationEmployee findByToken(String token);

	
	
}
