package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDAOImplementation;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.request.EmployeeRequest;
import com.example.demo.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;

// @Controller + @ResponseBody
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@Autowired
	private DepartmentRepository dRepo;
	
	@Autowired
	private EmployeeDAOImplementation eDAO;
	
	@Value("${app.name: Employee Tracker}")
	private String appName;
	

	@Value("${app.version: version1}")
	private String appVersion;
	
	
	@GetMapping("/version")
	public String getAppDetails() {
		return appName+" - "+appVersion;
	}
	
	
	//@RequestMapping(value="/employees", method="RequestMethod.GET")
	/*
	 * @GetMapping("/employees") public ResponseEntity<List<Employee>>
	 * readEmployeeByAll() { return new
	 * ResponseEntity<List<Employee>>(eService.readEmployeeByAll(), HttpStatus.OK);
	 * }
	 */
	

	 	
	//localhost:8080/employees/12
	@GetMapping("/employees/{id}")
	//public String getEmployee(@PathVariable("id") Long id) {
	public  ResponseEntity<Employee> readEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<Employee>(eService.readEmployeeById(id), HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/employees") public ResponseEntity<Employee>
	 * createEmployeeBySave(@Valid @RequestBody Employee employee) { return new
	 * ResponseEntity<Employee>(eService.createEmployeeBySave(employee),
	 * HttpStatus.CREATED); }
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeResponse>> readEmployeeByAll() {
		List<Employee> list = eRepo.findAll();
		List<EmployeeResponse> responseList = new ArrayList<>();
		list.forEach(e -> {
			EmployeeResponse eResponse = new EmployeeResponse();
			eResponse.setId(e.getId());
			eResponse.setEmployeeName(e.getName());
			List<String> depts = new ArrayList<>();
			for(Department d: e.getDepartments()) {
				depts.add(d.getName());
			}
			eResponse.setDepartment(depts);
			responseList.add(eResponse);
		});
		return new ResponseEntity<List<EmployeeResponse>>(responseList, HttpStatus.OK);
	}
	
	
	@PostMapping("/employees")
	public ResponseEntity<String> createEmployeeBySave(@Valid @RequestBody EmployeeRequest eReq) {
		Employee emp = new Employee(eReq);
		emp = eRepo.save(emp);
		
		for(String s: eReq.getDepartment()) {
			Department d = new Department();
			d.setName(s);
			d.setEmployee(emp);
			
			dRepo.save(d);
		}
		return new ResponseEntity<String>("Record saved successfully", HttpStatus.CREATED);
	}

	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeBySave(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployeeBySave(employee), HttpStatus.OK);
	}
	
	//localhost:8080/employees?id=12
	@DeleteMapping("/employees")
	//public String deleteEmployee(@RequestParam("id") Long id) {
	public ResponseEntity<HttpStatus> deleteEmployeeById(@RequestParam Long id) {
		eService.deleteEmployeeById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> readEmployeeByName(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eService.readEmployeeByName(name), HttpStatus.OK);
	}
	
		
	@GetMapping("/employees/filter/all")
	public ResponseEntity<List<Employee>> readEmployeeByAll(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(eDAO.getAll(), HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/employees/filter/{name}") public ResponseEntity<List<Employee>>
	 * readEmployeeByDepartmentName(@PathVariable String name){ return new
	 * ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name),
	 * HttpStatus.OK); }
	 */
}
