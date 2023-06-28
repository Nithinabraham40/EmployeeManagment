package com.tutorial.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.employee.dto.EmployeeInputDto;
import com.tutorial.employee.dto.EmployeeOutputDto;
import com.tutorial.employee.dto.EmployeeRecordDto;
import com.tutorial.employee.dto.HrSignInDto;
import com.tutorial.employee.model.EmployeeRecord;
import com.tutorial.employee.model.Hr;
import com.tutorial.employee.service.HrService;

@RestController
@RequestMapping(value= "hr")
public class HrController {
	
	
	
	@Autowired
	private HrService hrService;
	
	
	@PostMapping("/signup")
	
	public ResponseEntity<String>signUp(@RequestBody Hr hr){
		
		
		return hrService.signUp(hr);
	}
	
	@GetMapping("/signin")
	
	public ResponseEntity<String>signin(@RequestBody HrSignInDto input){
		
		return hrService.signin(input);
	}
	
	@PostMapping("add/employee/{token}/{email}")
	
	public ResponseEntity<String>addEmployee(@PathVariable("token") String token,@PathVariable("email") String email,@RequestBody EmployeeInputDto input ){
		
		
		
		return hrService.addEmpoyee(token,email,input);
		
	}
	@GetMapping("get/employess/{token}/{email}")
	
	public ResponseEntity<List<EmployeeOutputDto>>getEmployee(@PathVariable("token") String token,@PathVariable("email") String email){
		
		
		return hrService.getEmployee(token,email);
		
		
	}
		
		
		
	
	
	
	
	
	@PostMapping("add/employee/records/{token}/{email}")
	
	public ResponseEntity<String>addRecords(@PathVariable("token") String token,@PathVariable("email") String email,@RequestBody EmployeeRecordDto records){
		
		
		return hrService.addRecords(token,email,records);
	}
	
	
	@GetMapping("get/emprecords/{token}/{email}")
	
	public ResponseEntity<List<EmployeeRecordDto>>getEmployeeRecord(@PathVariable("token")String token,@PathVariable("email")String email){
		
		
		return hrService.getEmployeeRecords(token,email);
	}
	
	@PutMapping("update/employee/record/{token}/{email}/{empid}")
	
	public ResponseEntity<String>updateRecord(@PathVariable("token")String token,@PathVariable("email")String email,@RequestBody EmployeeRecordDto updatedRecord,@PathVariable("empid")Long id){
		
		return hrService.updateRecord(token,email,updatedRecord,id);
		
	}
	
	@DeleteMapping("/signout/{token}/{email}")
	
	public ResponseEntity<String>signOut(@PathVariable("token")String token,@PathVariable("email")String email){
		
		return hrService.signOut(token,email);
	}
	
	
	
	
	

}
