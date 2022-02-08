package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository eRepository;

	@Override
	public List<Employee> readEmployeeByAll() {
		return eRepository.findAll();
	}
	
	@Override
	public Employee createEmployeeBySave(Employee employee) {
		return eRepository.save(employee);		
	}

	@Override
	public Employee readEmployeeById(Long id) {
		Optional<Employee> employee = eRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		throw new RuntimeException("Employ is not found for id: "+id);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		eRepository.deleteById(id);		
	}


	@Override
	public Employee updateEmployeeBySave(Employee employee) {
		return eRepository.save(employee);
	}

	@Override
	public List<Employee> readEmployeeByName(String name) {
		return eRepository.findByName(name);
	}

	

	/*
	 * private static List<Employee> list = new ArrayList<Employee>();
	 * 
	 * static { Employee e = new Employee(); e.setName("scp"); e.setAge(52L);
	 * e.setLocation("Korea"); e.setEmail("sss@naver.com"); e.setDepartment("IT");
	 * list.add(e);
	 * 
	 * e = new Employee(); e.setName("scp"); e.setAge(20L); e.setLocation("India");
	 * e.setEmail("sss@naver.com"); e.setDepartment("HR"); list.add(e); }
	 */
	


}
