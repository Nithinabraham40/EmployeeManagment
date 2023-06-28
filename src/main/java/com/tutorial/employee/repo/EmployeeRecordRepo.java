package com.tutorial.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tutorial.employee.model.EmployeeRecord;

public interface EmployeeRecordRepo extends JpaRepository<EmployeeRecord, Long>{

	
	@Query(
			value = "select * from tbl_record where fk_emp_id=:empId",
			nativeQuery = true
			)
	
	EmployeeRecord findByempId(Long empId);

	@Query(
			value = "select * from tbl_record where fk_hr_id=:hrId",
			nativeQuery = true
			)
	
	
	List<EmployeeRecord> getAllEmployeeByfkHrid(Long hrId);

	

}
