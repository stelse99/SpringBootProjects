package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "academy_id")
	private List<Subject> subjects = new ArrayList<>();
	
	@Builder
	public Academy(String name, List<Subject> subjects) {
		this.name = name;
		if(subjects != null) {
			this.subjects = subjects;
		}
	}
	
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
		subject.updateAcademy(this);			
	}

}
