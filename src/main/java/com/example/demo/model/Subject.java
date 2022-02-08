package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academy_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_ACADEMY"))
	private Academy academy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_TEACHER"))
	private Teacher teacher;
	
	@Builder
	public Subject(String name, Academy academy, Teacher teacher) {
		this.name = name;
		this.academy = academy;
		this.teacher = teacher;
	}
	
	public void updateAcademy(Academy academy) {
		this.academy = academy;		
	}
}
