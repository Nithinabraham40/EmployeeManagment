package com.tutorial.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.employee.model.Hr;

public interface HrRepo extends JpaRepository<Hr, Long> {

	Hr findFirstByEmail(String hrEmail);
	
	
	

}
