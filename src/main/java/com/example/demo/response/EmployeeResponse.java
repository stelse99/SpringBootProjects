package com.example.demo.response;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeResponse {
	
	private Long id;
	
	private String employeeName;
	
	private List<String> department;

}
