# Welcome to readme-md-generator &#x1F44B; 
![example workflow](https://img.shields.io/badge/Eclipse-Version%3A%202022--09%20(4.25.0)-orange)
![example workflow](https://img.shields.io/badge/SpringBoot-2.2.1-yellowgreen)
![example workflow](https://img.shields.io/badge/Java-8-yellowgreen)
![example workflow](https://img.shields.io/badge/Postman-v10.13-orange)
![example workflow](https://img.shields.io/badge/Documentation-Yes-green)
![example workflow](https://img.shields.io/badge/Manitained%3F-Yes-green)
 >CLI that generate beautiful **ReadME**.md files

  :house:  <b><span style="color: blue;">Homepage</span></b>
  


 # Prerequisties

 - **Eclipse >=4.55.0**
 - **Postman >=10.13**
 


# Install
```
Maven Install
SpringTool Install
```
 # Framework And Language

 - **Framework :  SpringBoot**
 - **Language :  Java**

 # Dependencies Required

 
 - **spring-boot-starter-web**
 - **spring-boot-devtools**
 - **spring-boot-starter-data-jpa**
 - **spring-boot-starter-validation**
 
 - **mysql-connector**
 - **lambok**
 - **jbcrypt**

 - **spring-boot-starter-test**
 


# Models Used



 - **HR**
 -  **Employee**
 -  **AuthenticationHr**
 -  **AuthenticationEmployee**
 -  **EmployeeRecord**
  
 


	
	



#  Data flow

- **User send a request to ApI endpoint**
- **api forward it to the controller**
- **controller forward it to the Service layer**
- **service layer provide the necessary business logic and ask the repository for data**
- **Repository fetch the data from Mysql and give it back to service layer**
- **service layer give it to controller**
- **contoller give it to api**
- **Api give the response to user**


#  Api end points used at HR Controller

- **hr/signup**
- **hr/signin**
- **hr/add/employee/{token}/{email}**
- **hr/get/employess/{token}/{email}**
- **hr/add/employee/records/{token}/{email}**
- **hr/get/emprecords/{token}/{email}**
- **hr/update/employee/record/{token}/{email}/{empid}**
- **hr/signout/{token}/{email}**



#  Api end points used at Employee Controller

- **employee/signin**
- **employee/get/myrecord/{token}/{email}**
-  **employee/signout**








# About my EmployeeManagment Project

 
🌱 The project 🏢 Employeemanagment is a 🌟 spring boot project which aims to 🤝 help the HR 🕴️ of different departments 📚 to ✨ efficiently control, add, and get all the employees 🧑‍💼 of their respective departments. The HR personnel 🤵‍♀️ can add employees 🆕 to the system, providing their details 📝. Additionally, they have the ability to create credentials 🔐 for the employees, allowing them to log in and access their records 📋. The HR team 🧑‍🤝‍🧑 can add and update the records as needed, ensuring accurate information is maintained. At the end, both the employees and HR personnel can safely sign out 🚪 to ensure the privacy of their accounts. Furthermore, the project provides a convenient sign up ✍️ and sign in 🔑 process specifically designed for HR personnel. 🌟
.


#   HR Controller
```
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
```



#   Employee Controller
```


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
	
	@DeleteMapping("/signout/{token}/{email}")
	
	public ResponseEntity<String>signout(@PathVariable("token") String token,@PathVariable("email") String email){
		
		
		return employeeService.sigOut(token,email);
	}
	
	
	
}



```







	
	


  



	



# DataBase Used


*Mysql*










  






# :handshake:  Contributing
  Contributions,issues and features request are welcome! 
  

  #


  This *README* was generated with &#x2764;&#xFE0F; by <b><span style="color: blue;">readme-md-generator</span></b> 
