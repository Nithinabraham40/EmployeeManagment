package com.tutorial.employee.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial.employee.dto.EmployeeRecordDto;
import com.tutorial.employee.dto.EmployeeSignInInput;
import com.tutorial.employee.model.AuthenticationEmployee;
import com.tutorial.employee.model.Employee;
import com.tutorial.employee.model.EmployeeRecord;
import com.tutorial.employee.repo.AuthenticationEmployeeRepo;
import com.tutorial.employee.repo.EmployeeRecordRepo;
import com.tutorial.employee.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private AuthenticationEmployeeRepo authEmployeeRepo;
	
	
	@Autowired
	private EmployeeRecordRepo empRecordRepo;

	public ResponseEntity<String> signIn(EmployeeSignInInput signinInput) {
		
		String employeeEmail=signinInput.getEmail();
		String password=signinInput.getPassword();
		
		boolean check=validateEmailAndPassword(employeeEmail,password);
		
		if(check==false) {return new ResponseEntity<String>("Verification fail try again",HttpStatus.BAD_GATEWAY);}
		Employee employee=employeeRepo.findFirstByEmail(employeeEmail);
		AuthenticationEmployee auth=new AuthenticationEmployee(employee);
		
		authEmployeeRepo.save(auth);
		
		
		return new ResponseEntity<String>("Token is "+auth.getToken(),HttpStatus.OK);
	}

	private boolean validateEmailAndPassword(String employeeEmail, String password) {
		
		Employee employee=employeeRepo.findFirstByEmail(employeeEmail);
		if(employee==null) {return false;}
		
		String encryptPassword=employee.getPassword();
		
		boolean check=verifyPassword(password, encryptPassword);
		
		if(check==false) {
			return false;
		}
		
		return true;
	}
	
	
	

      //verify password
      
	   private boolean verifyPassword(String password, String hashedPassword) {
	    return BCrypt.checkpw(password, hashedPassword);
	}

	public ResponseEntity<EmployeeRecordDto> getRecord(String token, String email) {
		
		
		boolean check=validateTokenAndEmail(token,email);
		
		if(check==false) {
			
			EmployeeRecordDto record=EmployeeRecordDto.builder().attendence("Not found").discription("Not Found").education("Not found")
					.emp_id(null).build();
			
			
			return new ResponseEntity<EmployeeRecordDto>(record,HttpStatus.BAD_GATEWAY);}
		
		
		
		Long empId=employeeRepo.findFirstByEmail(email).getEmp_id();
		
		EmployeeRecord employeeRecord=empRecordRepo.findByempId(empId);
		
		EmployeeRecordDto record=EmployeeRecordDto.builder().attendence(employeeRecord.getAttendence()).discription(employeeRecord.getDiscreption())
				.education(employeeRecord.getEducation()).emp_id(employeeRecord.getEmployee().getEmp_id()).isInNoticePeroiod(employeeRecord.isInNoticePeroiod())
				.keySkills(employeeRecord.getKeySkills()).performance(employeeRecord.getPerformance()).projectsWorkingOn(employeeRecord.getProjectsWorkingOn()).build();
		
		
		
		
		return new ResponseEntity<EmployeeRecordDto>(record,HttpStatus.OK);
	}

	private boolean validateTokenAndEmail(String token, String email) {
		
		AuthenticationEmployee auth=authEmployeeRepo.findByToken(token);
		
		if(auth==null) {
			return false;
		}
		
		Long empId=auth.getEmployee().getEmp_id();
		Employee employee=employeeRepo.findById(empId).get();
		String empEmail=employee.getEmail();
		
		if(!empEmail.equals(email)) {
			return false;
		}
		
		
		return true;
	}
	
	
}
