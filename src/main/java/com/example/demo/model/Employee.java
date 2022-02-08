package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.demo.request.EmployeeRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@NamedNativeQuery(name = "getAllRecords", query = "select * from tbl_employee", resultClass = Employee.class)
@Table(name="tbl_employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonProperty("full_name")
	//@NotNull(message = "Name should not be null")
	//@NotEmpty(message = "Name should not be empty")
	//@NotBlank(message = "Name should not be null")
	private String name;
	
	@OneToMany(mappedBy = "employee")
	private List<Department> departments;
	
	public Employee(EmployeeRequest eReq) {
		this.name = eReq.getName();
	}
	
	
	/*
	 * //@JsonIgnore private Long age = 0L;
	 * 
	 * private String location;
	 * 
	 * @Email(message = "Please enter the valid email address") private String
	 * email;
	 * 
	 * //@NotNull(message = "Department should not be null") //@NotEmpty(message =
	 * "Department should not be empty")
	 * 
	 * @NotBlank(message = "Department should not be null") private String
	 * department;
	 * 
	 * @CreationTimestamp
	 * 
	 * @Column(name="created_at", nullable = false, updatable = false) private
	 * LocalDateTime createdAt = LocalDateTime.now(); //private Date createAt;
	 * 
	 * @UpdateTimestamp
	 * 
	 * @Column(name="updated_at") private LocalDateTime updatedAt =
	 * LocalDateTime.now(); //private String localDateTime =
	 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"
	 * ))
	 */	
	
}
