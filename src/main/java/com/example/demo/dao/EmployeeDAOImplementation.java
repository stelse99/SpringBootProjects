package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeDAOImplementation {

	@PersistenceContext
	private EntityManager manager;
		
	public List<Employee> getAll(){
		return manager.createNamedQuery("getAllRecords", Employee.class).getResultList();
	}
	
}
