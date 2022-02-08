package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByName(String name);
	
	//List<Employee> findByDepartmentName(String name);
	
	//@Query("FROM Employee WHERE department.name = :name")
	//List<Employee> getEmployeeByDeptName(String name);
	
	@Query(value = "select * from tbl_employee", nativeQuery = true)
	List<Employee> getEmployees();

}