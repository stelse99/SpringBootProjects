package com.example.demo.request;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeRequest {

	private String name;
	
	private List<String> department;
	
}
