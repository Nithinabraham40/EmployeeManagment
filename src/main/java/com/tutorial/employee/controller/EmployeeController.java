package com.tutorial.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.tutorial.employee.dto.EmployeeRecordDto;
import com.tutorial.employee.dto.EmployeeSignInInput;
import com.tutorial.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	@PostMapping("/signin")
	
	public ResponseEntity<String>signIn(@RequestBody EmployeeSignInInput signinInput){
		
		
		return employeeService.signIn(signinInput);
		
	}
	
	@GetMapping("/get/myrecord/{token}/{email}")
	
	public ResponseEntity<EmployeeRecordDto>getRecord(@PathVariable("token") String token,@PathVariable("email") String email){
		
		return employeeService.getRecord(token,email);
	}
	
	
	
}
