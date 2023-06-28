package com.tutorial.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial.employee.dto.EmployeeInputDto;
import com.tutorial.employee.dto.EmployeeOutputDto;
import com.tutorial.employee.dto.EmployeeRecordDto;
import com.tutorial.employee.dto.HrSignInDto;
import com.tutorial.employee.model.AuthenticationHr;
import com.tutorial.employee.model.Employee;
import com.tutorial.employee.model.EmployeeRecord;
import com.tutorial.employee.model.Hr;
import com.tutorial.employee.repo.AuthentictionHrRepo;
import com.tutorial.employee.repo.EmployeeRecordRepo;
import com.tutorial.employee.repo.EmployeeRepo;
import com.tutorial.employee.repo.HrRepo;

@Service
public class HrService {

	
	
	@Autowired
	private HrRepo hrRepo;
	
	
	@Autowired
	private AuthentictionHrRepo authHrRepo;
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private EmployeeRecordRepo empRecordRepo;
	
	

	public ResponseEntity<String> signUp(Hr hr) {
		
		
		String hrEmail=hr.getEmail();
		
		Hr findHr=hrRepo.findFirstByEmail(hrEmail);
		
		if(findHr!=null) {
			
			return new ResponseEntity<String>("Email alreay exit",HttpStatus.BAD_REQUEST);
		}
		
		String hrPassword=hr.getPassword();
		String encryptHrpassord=encryptPassword(hrPassword);
		
		hr.setPassword(encryptHrpassord);
		
		hrRepo.save(hr);
		

		
		return new ResponseEntity<String>("SignUp Sucess",HttpStatus.OK);
	}
	
	
	
	
	 private String encryptPassword(String password) {
	      
	     String salt = BCrypt.gensalt();

	     
	     String hashedPassword = BCrypt.hashpw(password, salt);

	     return hashedPassword;
	 }

      //verify password
      
	   private boolean verifyPassword(String password, String hashedPassword) {
	    return BCrypt.checkpw(password, hashedPassword);
	}




	public  ResponseEntity<String> signin(HrSignInDto input) {
		
		String inputedEmail=input.getEmail();
		String inputedPassword=input.getPassword();
		
		boolean check=verifyPasswordAndEmail(inputedEmail,inputedPassword);
		if(check==false) {return new ResponseEntity<String>("Invalid email or password",HttpStatus.BAD_GATEWAY);}
		
		Hr hr=hrRepo.findFirstByEmail(inputedEmail);
		AuthenticationHr authentication=new AuthenticationHr(hr);
		authHrRepo.save(authentication);
		

		return new ResponseEntity<String>("Token is "+authentication.getToken(),HttpStatus.OK);
	}




	private boolean verifyPasswordAndEmail(String inputedEmail, String inputedPassword) {
		
		Hr hr=hrRepo.findFirstByEmail(inputedEmail);
		if(hr==null) {return false;}
		
		String encryptedPassword=hr.getPassword();
		
		boolean check=verifyPassword(inputedPassword, encryptedPassword);
		if(check==false) {return false;}
		
		
		return true;
	}




	public ResponseEntity<String> addEmpoyee(String token, String email, EmployeeInputDto input) {
		
		boolean check=verifyTokenAndEmail(token,email);
		
		if(check==false) {
			
			return new ResponseEntity<String>("Authentication failed",HttpStatus.BAD_GATEWAY);
		}
		
		String employeePassword=input.getPassword();
		String encryptPassword=encryptPassword(employeePassword);
		
		Hr hr1=hrRepo.findFirstByEmail(email);
		Employee emp=Employee.builder().annualSalary(input.getAnnualSalary()).depertment(hr1.getDepManaged()).email(input.getEmail())
				.experience(input.getExperience()).name(input.getName()).password(encryptPassword).hr(hr1).build();
		
		empRepo.save(emp);
		
		
		return new ResponseEntity<String>("Employee details added",HttpStatus.OK);
	}




	private boolean verifyTokenAndEmail(String token, String email) {
	
		
		AuthenticationHr authentication=authHrRepo.findFirstByToken(token);
		
		if(authentication==null) {
			return false;
		}
		
		String hrMail=authentication.getHr().getEmail();
		
		if(!hrMail.equals(email)) {
			return false;
		}
		
		
		return true;
	}




	public ResponseEntity<String> addRecords(String token, String email, EmployeeRecordDto records) {
		
          boolean check=verifyTokenAndEmail(token,email);
		
		if(check==false) {
			
			return new ResponseEntity<String>("Authentication failed",HttpStatus.BAD_GATEWAY);
		}
		
	Employee employee=empRepo.findById(records.getEmp_id()).get();

	Hr hr1=hrRepo.findFirstByEmail(email);
		
		EmployeeRecord empRecord=EmployeeRecord.builder().attendence(records.getAttendence()).discreption(records.getDiscription())
				.education(records.getEducation()).isInNoticePeroiod(records.isInNoticePeroiod()).performance(records.getPerformance())
				.projectsWorkingOn(records.getProjectsWorkingOn()).hr(hr1).employee(employee).keySkills(records.getKeySkills()).build();
		
		
		empRecordRepo.save(empRecord);
		
		return new ResponseEntity<String>("Record added",HttpStatus.OK);
	}




	public ResponseEntity<List<EmployeeOutputDto>> getEmployee(String token, String email) {
	
		  boolean check=verifyTokenAndEmail(token,email);
			
			if(check==false) {
				
				
				List<EmployeeOutputDto>ls =new ArrayList<>();
				EmployeeOutputDto output=new EmployeeOutputDto();
				ls.add(output);
				
				
				return new ResponseEntity<List<EmployeeOutputDto>>(ls,HttpStatus.BAD_GATEWAY);
			}
			
			Long hrId=hrRepo.findFirstByEmail(email).getHr_id();
			
			List<Employee>empList=empRepo.getAllEmployeeWithFkhr(hrId);
			
			List<EmployeeOutputDto>ls=new ArrayList<>();
			
			for(Employee emp:empList) {
				
				EmployeeOutputDto empOutput=EmployeeOutputDto.builder().annualSalary(emp.getAnnualSalary()).email(emp.getEmail())
                      .experience(emp.getExperience()).name(emp.getName()).build();
				ls.add(empOutput);
			}
			
			return new ResponseEntity<List<EmployeeOutputDto>>(ls,HttpStatus.OK);
	}




	public ResponseEntity<List<EmployeeRecordDto>> getEmployeeRecords(String token, String email) {
		
		
		
		 boolean check=verifyTokenAndEmail(token,email);
	
	if(check==false) {
		
		
		List<EmployeeRecordDto>ls =new ArrayList<>();
		EmployeeRecordDto output=new EmployeeRecordDto();
		ls.add(output);
		
		
		return new ResponseEntity<List<EmployeeRecordDto>>(ls,HttpStatus.BAD_GATEWAY);
	}
	Long hrId=hrRepo.findFirstByEmail(email).getHr_id();
	
	List<EmployeeRecord>lsEmpRecord=empRecordRepo.getAllEmployeeByfkHrid(hrId);
	
	List<EmployeeRecordDto>lsOut=new ArrayList<>();
	
	for(EmployeeRecord record:lsEmpRecord) {
		
		EmployeeRecordDto empRecDto=EmployeeRecordDto.builder().attendence(record.getAttendence()).discription(record.getDiscreption())
				.education(record.getEducation()).emp_id(record.getEmployee().getEmp_id()).isInNoticePeroiod(record.isInNoticePeroiod())
				.keySkills(record.getKeySkills()).performance(record.getPerformance()).projectsWorkingOn(record.getProjectsWorkingOn())
				.build();
		lsOut.add(empRecDto);
	}
		

	return new ResponseEntity<List<EmployeeRecordDto>>(lsOut,HttpStatus.OK);
	}




	public ResponseEntity<String> updateRecord(String token, String email, EmployeeRecordDto updatedRecord,Long id) {
		
        boolean check=verifyTokenAndEmail(token,email);
		
		if(check==false) {
			
			return new ResponseEntity<String>("Authentication failed",HttpStatus.BAD_GATEWAY);
		}
		
		EmployeeRecord oldRecord=empRecordRepo.findByempId(id);
		
		empRecordRepo.delete(oldRecord);
		Employee employee1=empRepo.findById(id).get();
		Hr hr1=hrRepo.findFirstByEmail(email);
		
		oldRecord=EmployeeRecord.builder().attendence(updatedRecord.getAttendence()).discreption(updatedRecord.getDiscription())
				.education(updatedRecord.getEducation()).isInNoticePeroiod(updatedRecord.isInNoticePeroiod()).keySkills(updatedRecord.getKeySkills())
				.performance(updatedRecord.getPerformance()).projectsWorkingOn(updatedRecord.getProjectsWorkingOn()).employee(employee1).hr(hr1).build();
		
		empRecordRepo.save(oldRecord);
		
		return new ResponseEntity<String>("Updated the Record",HttpStatus.OK);
	}




	public ResponseEntity<String> signOut(String token, String email) {
		
		
           boolean check=verifyTokenAndEmail(token,email);
		
		    if(check==false) {
		   	
			return new ResponseEntity<String>("Authentication failed",HttpStatus.BAD_GATEWAY);
		}
	
		AuthenticationHr auth=authHrRepo.findFirstByToken(token);
		authHrRepo.delete(auth);
		return new ResponseEntity<String>("SignOut sucessfully",HttpStatus.OK);
	}
	
	
	
	
	
}
