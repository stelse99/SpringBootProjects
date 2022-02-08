package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	List<Employee> readEmployeeByAll();
	
	Employee createEmployeeBySave(Employee employee);
	
	Employee readEmployeeById(Long id);
	
	void deleteEmployeeById(Long id);
	
	Employee updateEmployeeBySave(Employee employee);
	
	List<Employee> readEmployeeByName(String name);
	
}
