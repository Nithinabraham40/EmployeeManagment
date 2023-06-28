package com.tutorial.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutorial.employee.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findFirstByEmail(String employeeEmail);

	
	
	@Query(
			value = "Select * from tbl_employee where fk_hr_id=:hrId",
			nativeQuery = true
			)
	List<Employee> getAllEmployeeWithFkhr(Long hrId);

	
	

}
