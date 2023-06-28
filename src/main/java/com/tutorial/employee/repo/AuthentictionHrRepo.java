package com.tutorial.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.employee.model.AuthenticationHr;

public interface AuthentictionHrRepo extends JpaRepository<AuthenticationHr, Long> {

	AuthenticationHr findFirstByToken(String token);
	
	
	

}
